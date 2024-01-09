package com.stocklisting.favourite.kafkaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.google.gson.Gson;
import com.stocklisting.favourite.model.FavouriteStock;
import com.stocklisting.favourite.service.FavouriteStockService;

@Configuration
public class KafkaListner {
	
	@Autowired
    FavouriteStockService favService;
	
	@Autowired
	private Gson gson;

    @KafkaListener(topics = AppConstant.USER_TOPIC_NAME,groupId = AppConstant.GROUP_ID)
    public void addUserDetails(String user){
    	FavouriteStock userObj = gson.fromJson(user, FavouriteStock.class);
    	//FavouriteStock result = favService.saveUserDetails(userObj);
        System.out.println(userObj);

    }

}

