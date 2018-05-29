
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BalanceRepository balanceRepository;
    
    @RequestMapping("/checkbalance")
    public String checkBalance(String username, int pin){
        
        if(userRepository.userExist(username)==null){
            return "klienti ei eksiteeri";
        }
        return Integer.toString(balanceRepository.checkBalance(username, pin));
    }
            
    @RequestMapping("/depositmoney")
    public String depositMoney(String username, int pin, int amount){
        if(userRepository.userExist(username)==null){
            return "klienti ei eksiteeri";
        }
        int userId = userRepository.getUserId(username, pin);
        updateHistory(userId, amount);
        
        balanceRepository.depositMoney(amount, username, pin);
        
        return "raha edukalt sisestatud!";
    }
    
    @RequestMapping("/withdrawmoney")
    public String withdrawMoney(String username, int pin, int amount){
        if(userRepository.userExist(username)==null){
            return "klienti ei eksiteeri";
        }
        int userId = userRepository.getUserId(username, pin);
        updateHistory(userId, -amount);
        
        balanceRepository.withdrawMoney(amount, username, pin);
        
        return "raha edukalt väljavõetud!";
    }
    
    
    public void updateHistory(int userId,int amount){
        
        balanceRepository.updateHistory(userId, amount);
    }
    
   @RequestMapping("/showhistory")
   public String showHistory(String username, int pin){
       List<Integer> list;
        
        try{
            list = balanceRepository.showHistory(username, pin);
        }catch(EmptyResultDataAccessException e){
            return "ajalugu puudub";
        }
       String vastus = "<ul>";
       for( Integer element : list){
           vastus+="<li>"+Integer.toString(element)+"</li>";
           
       }
       vastus+="</ul>";
       
       return vastus;
   }
   
   @RequestMapping("/showallhistory")
   public String showAllHistory(){
       List<Integer> list;
        
        try{
            list = balanceRepository.showAllHistory();
        }catch(EmptyResultDataAccessException e){
            return "ajalugu puudub";
        }
       String vastus = "<ul>";
       for( Integer element : list){
           vastus+="<li>"+Integer.toString(element)+"</li>";
           
       }
       vastus+="</ul>";
       
       return vastus;
   }
    
}
