
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BalanceRepository balanceRepository;
    
    @RequestMapping("/adduser")
    public String createUser(String username, int pin){
        
        
        String existingUsername = userRepository.userExist(username);
        if(existingUsername != null){
            return "kasutajanimi juba olemas";
        }
        int userId = addUser(username, pin);
        balanceRepository.createBalance(userId);
        return "kasutaja loodud!";
    }
    
    private int addUser(String username, int pin){
        
        userRepository.insertUser(username, pin);
        
        int userId = userRepository.getUserId(username, pin);
        
        return userId;
         
    }
    
    
  
    
    
    
}
