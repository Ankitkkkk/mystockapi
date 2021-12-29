package com.stocks.portfolio.service;

import com.stocks.portfolio.dao.AssetsRepository;
import com.stocks.portfolio.dao.StockRepository;
import com.stocks.portfolio.dao.UserRepository;
import com.stocks.portfolio.entity.AllVars;
import com.stocks.portfolio.entity.Assets;
import com.stocks.portfolio.entity.Stocks;
import com.stocks.portfolio.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AssetsRepository assetsRepository;




    public User addUser(AllVars user){
        if(userRepository.existsByUserName(user.getUserName()) != true){
            User newUser = new User(user.getUserName(), user.getUserPass(), 0d);
            userRepository.save(newUser);
            return newUser;
        }
        return new User("User already exists",null,null);
    }

    public User addBalance(AllVars user){
        if(userRepository.existsByUserName(user.getUserName()) == true){
            User oldUser = userRepository.findByUserName(user.getUserName());
            if((oldUser.getUserPass()).equals(user.getUserPass())){
                oldUser.setBalance(oldUser.getBalance()+user.getBalance());
                userRepository.save(oldUser);
                return oldUser;
            }
        }
        return new User("Invalid Username or Password",null,null);
    }

    public List<Assets> purchase(AllVars user){
        if(userRepository.existsByUserName(user.getUserName()) == true){
            User oldUser = userRepository.findByUserName(user.getUserName());
            if((oldUser.getUserPass()).equals(user.getUserPass())){
                Double userBalance = oldUser.getBalance();
                Integer stockToBePurchase = user.getsCount();
                String stockName = user.getsName();
                stockName = stockName.toUpperCase();
                if(stockRepository.existsByStockName(stockName) == false){
                    return List.of(new Assets(null,"Stock Not exists",null,null));
                }
                Stocks stockData = stockRepository.findByStockName(stockName);
                if(user.getsCount() > stockData.getStockCount()){
                    return List.of(new Assets(null,"Stocks Not Available",null,null));
                }
                Double stockPrice = Double.valueOf(stockData.getStockPrice());
                Double costPrice = stockPrice*stockToBePurchase;
                if(costPrice > userBalance){
                    return List.of(new Assets(null,"Insufficient Balance",null,null));
                }
                List<Assets> userAssets = assetsRepository.findAllBySid(oldUser.getId());
                boolean found = false;
                //Checking stocks before modifying details
                System.out.println(userAssets);
                Assets findingAssets = new Assets();
                for(Assets i : userAssets){
                    if((i.getStockName()).equals(stockName)){
                        findingAssets = i;
                        found = true;
                        break;
                    }
                }
                if(found == false){
                    Assets newAsset = new Assets(oldUser.getId(),stockName,stockPrice,Long.valueOf(stockToBePurchase));
                    oldUser.setBalance(userBalance-(stockPrice*stockToBePurchase));
                    userRepository.save(oldUser);
                    assetsRepository.save(newAsset);

                }
                else{
                    Double oldTotal = findingAssets.getAverage()*findingAssets.getStockCount();
                    Double curTotal = stockPrice*stockToBePurchase;
                    Double newAvg;
                    try{
                        newAvg = (oldTotal+curTotal)/(stockToBePurchase+findingAssets.getStockCount());
                    }
                    catch (Exception e){
                        newAvg = (oldTotal+curTotal);
                    }
                    findingAssets.setAverage(newAvg);
                    findingAssets.setStockCount(findingAssets.getStockCount()+stockToBePurchase);
                    found = true;
                    oldUser.setBalance(userBalance-(stockPrice*stockToBePurchase));
                    userRepository.save(oldUser);
                    assetsRepository.save(findingAssets);
                }
                List<Assets> updatedAssets = assetsRepository.findAllBySid(oldUser.getId());
                stockData.setStockCount(stockData.getStockCount()-stockToBePurchase);
                stockRepository.save(stockData);
                //Checking stocks after modifying details
                System.out.println(updatedAssets);
                return updatedAssets;
            }
        }
        return List.of(new Assets(null,"Invalid Username or Password",null,null));
    }

    public Double getBalance(AllVars user){
        if(userRepository.existsByUserName(user.getUserName()) == true){
            User oldUser = userRepository.findByUserName(user.getUserName());
            if((oldUser.getUserPass()).equals(user.getUserPass())){
                return oldUser.getBalance();
            }
        }
        return Double.valueOf(-1);
    }

    public User changePassword(AllVars user){
        if(userRepository.existsByUserName(user.getUserName()) == true){
            User oldUser = userRepository.findByUserName(user.getUserName());
            if((oldUser.getUserPass()).equals(user.getUserPass())){
                oldUser.setUserPass(user.getNewPass());
                userRepository.save(oldUser);
                return oldUser;
            }
        }
        return new User("Invalid Username or password",null,null);
    }

    public List<Assets> sellStock(AllVars user){
        if(userRepository.existsByUserName(user.getUserName()) == true){
            User oldUser = userRepository.findByUserName(user.getUserName());
            if((oldUser.getUserPass()).equals(user.getUserPass())){
                String stockName = user.getsName();
                stockName = stockName.toUpperCase();
                List<Assets> curAssets = assetsRepository.findAllBySid(oldUser.getId());

                for(Assets i : curAssets){
                    if((i.getStockName()).equals(stockName)){
                        if(user.getsCount() > i.getStockCount()){
                            return List.of(new Assets(null,"To many Assets",null,null));
                        }
                        oldUser.setBalance(oldUser.getBalance() + (i.getAverage()*user.getsCount()));
                        userRepository.save(oldUser);
                        i.setStockCount(i.getStockCount()-user.getsCount());
                        assetsRepository.save(i);
                        Stocks stockData = stockRepository.findByStockName(stockName);
                        stockData.setStockCount(stockData.getStockCount()+user.getsCount());
                        stockRepository.save(stockData);
                        return assetsRepository.findAllBySid(oldUser.getId());
                    }
                }
                return List.of(new Assets(null,"To many Assets",null,null));
            }
        }
        return List.of(new Assets(null,"Invalid Username or password",null,null));
    }

}
