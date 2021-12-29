package com.stocks.portfolio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stocks.portfolio.dao.Admins;
import com.stocks.portfolio.entity.*;
import com.stocks.portfolio.service.AdminAction;
import com.stocks.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DashboardController {

    private String adminApiKey = "ea915b56-e1ea-434f-9809-fbb841729622";
    private String userApiKey = "1b435e55-9c92-4651-948c-a0d8bbb06d9c";
    @Autowired
    private AdminAction adminAction;
    @Autowired
    private Admins admins;
    @Autowired
    private UserService userService;

    @GetMapping("working")
    public List<Stocks> check(@RequestBody Stocks st){
        st.setStockPrice(st.getStockPrice()+100);
        return List.of(st);
    }

    @GetMapping("signup/admin")
    public Admin signUpAdmin(@RequestHeader("api-key") String apiKey, @RequestBody AllVars admin){
        if(apiKey.equals(adminApiKey)){
            return adminAction.adminSignUp(admin);
        }
        return new Admin("Invalid api Key","Invalid api key");
    }

    @PutMapping("/admin/update")
    public Admin changePassword(@RequestHeader("api-key") String apiKey, @RequestBody AllVars admin){
        if(apiKey.equals(adminApiKey)){
            return adminAction.updatePassword(admin);
        }
        return new Admin("Invalid api Key","Invalid api key");
    }

    //To Update stocks
    @PutMapping("/admin/stocks")
    public List<Stocks> updateStocks(@RequestHeader("api-key") String apiKey, @RequestBody AllVars admin){
        if(apiKey.equals(adminApiKey)){
            return adminAction.updateStocks(admin);
        }
        return List.of(new Stocks("Invalid api key",null,null));
    }

    @GetMapping("signup/user")
    public User addUser(@RequestHeader("api-key") String apiKey, @RequestBody AllVars user){
        if(apiKey.equals(userApiKey)){
            return userService.addUser(user);
        }
        return new User("Invalid Api Key",null,null);
    }

    @GetMapping("user/addbalance")
    public User addBalance(@RequestHeader("api-key") String apiKey, @RequestBody AllVars user){
        if(apiKey.equals(userApiKey)){
            return userService.addBalance(user);
        }
        return new User("Invalid Api Key",null,null);
    }

    @GetMapping("/user/purchase")
    public List<Assets> purchase(@RequestHeader("api-key") String apiKey, @RequestBody AllVars user){
        if(apiKey.equals(userApiKey)){
            return userService.purchase(user);
        }
        return List.of(new Assets(null,"invalid api key",null,null));
    }

    @GetMapping("/user/balance")
    public Double getBalance(@RequestHeader("api-key") String apiKey, @RequestBody AllVars user){
        if(apiKey.equals(userApiKey)){
            return userService.getBalance(user);
        }
        return Double.valueOf(-1);
    }

    @PutMapping("user/password")
    public User changePass(@RequestHeader("api-key") String apiKey, @RequestBody AllVars user){
        if(apiKey.equals(userApiKey)){
            return userService.changePassword(user);
        }
        return new User("Invalid api Key",null,null);
    }


    @GetMapping("user/sell")
    public List<Assets> sell(@RequestHeader("api-key") String apiKey, @RequestBody AllVars user){
        if(apiKey.equals(userApiKey)){
            return userService.sellStock(user);
        }
        return List.of(new Assets(null,"Invalid api Key",null,null));
    }
}
