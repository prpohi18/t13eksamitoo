
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository//näitab, et on DAO tüüpi
public class UserRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public int getUserId(String username,int pin){
        
        String sql = "SELECT userid FROM users WHERE username=? and pin=?";
        int userId = jdbcTemplate.queryForObject(sql,int.class,username,pin);
        return userId;
    }
    
    public void insertUser(String username, int pin){
        
        String sql1 = "INSERT INTO users(username,pin) VALUES(?,?)";
        jdbcTemplate.update(sql1,username,pin);
    }
    
    
    public String userExist(String username){
        
        String sql = "SELECT username FROM users WHERE username=?";
        try{
        String userName = jdbcTemplate.queryForObject(sql,String.class,username);
        
        return userName;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
        
    }
}

