package com.stocklisting.favourite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocklisting.favourite.model.FavouriteStock;
import com.stocklisting.favourite.service.FavouriteStockService;

@CrossOrigin
@RestController
@RequestMapping(path = "/favourite")
public class FavouriteStockController {
	
	@Autowired
	FavouriteStockService favService ;
	
	@PostMapping("/addStock")
	ResponseEntity<?> addFavStock(@RequestBody FavouriteStock fstock){
		FavouriteStock stockObj = favService.addFavouriteStock(fstock);
		if(stockObj == null) {
			Map<String,String> res = new HashMap<>();	
			res.put("message", "Stock is already exist in the favStockList.");
			return new ResponseEntity<Map>(res,HttpStatus.BAD_REQUEST);
		}
		System.out.println(stockObj);
		return new ResponseEntity<FavouriteStock>(stockObj,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/addStock/{username}")
	ResponseEntity<?> addFavStockToUsername(@RequestBody FavouriteStock fstock, @PathVariable String username) {
        
		FavouriteStock stockObj = favService.addStockForUsername(fstock, username);
		if(stockObj == null) {
			Map<String,String> res = new HashMap<>();	
			res.put("message", "Stock is already exist in the favStockList.");
			return new ResponseEntity<Map>(res,HttpStatus.BAD_REQUEST);
		}
		System.out.println(stockObj);
		return new ResponseEntity<FavouriteStock>(stockObj,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/viewAllStock")
	ResponseEntity<List<FavouriteStock>> viewAllFavStock(){
		List<FavouriteStock> stockList = favService.viewAllFavouriteStocks();
		return new ResponseEntity<List<FavouriteStock>>(stockList,HttpStatus.OK);
		
	}
	
	@GetMapping("/viewAllStock/{username}")
	ResponseEntity<List<FavouriteStock>> viewAllFavStockByUsername(@PathVariable("username") String username){
		List<FavouriteStock> stockList = favService.viewAllFavouriteStocksByUsername(username);
		return new ResponseEntity<List<FavouriteStock>>(stockList,HttpStatus.OK);
		
	}
	
	@GetMapping("/stock/{favouriteId}")
	ResponseEntity<FavouriteStock> viewFavStockById(@PathVariable("favouriteId") Long favouriteId){
		FavouriteStock stockObj = favService.viewFavouriteStockByFavId(favouriteId);
		return new ResponseEntity<FavouriteStock>(stockObj,HttpStatus.OK);
		
	}

	
	@DeleteMapping("/deleteStock/{favouriteId}")
	ResponseEntity<FavouriteStock> deleteFavStockById(@PathVariable Long favouriteId){
		FavouriteStock stockObj = favService.DeleteFavouriteStockByFavId(favouriteId);
		return new ResponseEntity<FavouriteStock>(stockObj,HttpStatus.OK);
		
	}
	

}
