package com.stocklisting.favourite.service;

import java.util.List;

import com.stocklisting.favourite.model.FavouriteStock;


public interface FavouriteStockService {
	
	public FavouriteStock addFavouriteStock(FavouriteStock fstock);
    public List<FavouriteStock> viewAllFavouriteStocks();
    public FavouriteStock viewFavouriteStockByFavId(Long favouriteId);
    public FavouriteStock DeleteFavouriteStockByFavId(Long favouriteId);
    public List<FavouriteStock> viewAllFavouriteStocksByUsername(String username);
    public FavouriteStock addStockForUsername(FavouriteStock stock, String username);
   // public FavouriteStock addStockForUsername(FavouriteStock fstock, String username);
   // public User saveUserDetails(User user);

}
