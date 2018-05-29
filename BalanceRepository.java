
package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void createBalance(int userId){
        
        String sql = "INSERT INTO balance(userid, balance) VALUES(?,0)";
        jdbcTemplate.update(sql,userId);
    
    }
    
    public Integer checkBalance(String username, int pin){
        
        String sql = "SELECT balance FROM balance JOIN users ON users.userid=balance.userid WHERE username=? and pin=?";
        try{
            Integer balance = jdbcTemplate.queryForObject(sql,Integer.class, username, pin);//int.class tee tagastuseks int väärtus
            return balance;
        }catch(EmptyResultDataAccessException e){
            return 0;
        }
    }
    
    
    public void depositMoney(int amount, String username, int pin){
        String sql = "UPDATE balance JOIN users ON users.userid=balance.userid SET balance=balance+? WHERE username=? and pin=?";
        jdbcTemplate.update(sql,amount, username, pin);
    }
    
    public void withdrawMoney(int amount, String username, int pin){
        String sql = "UPDATE balance JOIN users ON users.userid=balance.userid SET balance=balance-? WHERE username=? and pin=?";
        jdbcTemplate.update(sql,amount, username, pin);
    }
    
    public void updateHistory(int userId,int amount){
        
        String sql = "INSERT INTO balancehistory(userid, transaction) VALUES(?,?)";
        jdbcTemplate.update(sql,userId,amount);
    }
    
    public List<Integer> showHistory(String username, int pin){
        String sql = "SELECT transaction FROM balancehistory JOIN users ON users.userid=balancehistory.userid WHERE username=? and pin=?";
        
        return jdbcTemplate.queryForList(sql,Integer.class,username, pin);//int.class tee tagastuseks int väärtus
        
    }
    
    public List<Integer> showAllHistory(){
        String sql = "SELECT transaction FROM balancehistory";
        
        return jdbcTemplate.queryForList(sql,Integer.class);//int.class tee tagastuseks int väärtus
        
    }
}
