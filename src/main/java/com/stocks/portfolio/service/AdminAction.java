package com.stocks.portfolio.service;

import com.stocks.portfolio.dao.Admins;
import com.stocks.portfolio.dao.AssetsRepository;
import com.stocks.portfolio.dao.StockRepository;
import com.stocks.portfolio.dao.UserRepository;
import com.stocks.portfolio.entity.Admin;
import com.stocks.portfolio.entity.AllVars;
import com.stocks.portfolio.entity.Stocks;
import com.stocks.portfolio.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class AdminAction {

    @Autowired
    private Admins admins;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssetsRepository assetsRepository;

    public Admin adminSignUp(AllVars admin){
        // Condition for checking if Admin name already exists
        if(admins.existsByAdminName(admin.getAdminName()) == false){
            Admin myadmin = new Admin(admin.getAdminName(),admin.getAdminPass());
            admins.save(myadmin);
            return admins.findByAdminName(admin.getAdminName());
        }
        return new Admin("User Already Exists",null);
    }

    //To change Password of admin
    public Admin updatePassword(AllVars admin){
        //Checking if User Exists
        if(admins.existsByAdminName(admin.getAdminName()) == true){
            Admin adminData = admins.findByAdminName(admin.getAdminName());

            //checking if password matches
            if(adminData.getAdminPass().equals(admin.getAdminPass())){

                // Updating the Password
                adminData.setAdminPass(admin.getNewPass());
                admins.save(adminData);
                return admins.findByAdminName(admin.getAdminName());
            }
            return new Admin(admin.getAdminName(),"Password Not Matched");
        }
        return new Admin("User not Exists",null);
    }

    public List<Stocks> updateStocks(AllVars admin){
        //Checking if User Exists
        if(admins.existsByAdminName(admin.getAdminName()) == true){
            Admin adminData = admins.findByAdminName(admin.getAdminName());

            //checking if password matches
            if(adminData.getAdminPass().equals(admin.getAdminPass())){
                String stockName = admin.getsName();
                stockName = stockName.toUpperCase();
                if(stockRepository.existsByStockName(stockName) == true){
                    Stocks tmpStock = stockRepository.findByStockName(stockName);
                    //setting stock count
                    tmpStock.setStockCount(admin.getsCount()+ tmpStock.getStockCount());
                    //setting stock price
                    tmpStock.setStockPrice(admin.getsPrice()+ tmpStock.getStockPrice());
                    stockRepository.save(tmpStock);
                }
                else{
                    Stocks tmpStock = new Stocks(stockName,admin.getsCount(),admin.getsPrice());
                    stockRepository.save(tmpStock);
                }
                return stockRepository.findAll();
            }
            return List.of(new Stocks("Password didn't match",null,null));
        }
        return List.of(new Stocks("User does not Exists",null,null));
    }

}
