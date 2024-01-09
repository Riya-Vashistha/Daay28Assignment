package com.stocklisting.favourite.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stocklisting.favourite.model.FavouriteStock;

import com.stocklisting.favourite.repository.FavouriteStockRepository;


@Service
public class FavouriteStockServiceImpl implements FavouriteStockService{

	@Autowired
	FavouriteStockRepository favRepo;
	
	@Override
	public FavouriteStock addFavouriteStock(FavouriteStock fstock) {
		// TODO Auto-generated method stub
		FavouriteStock favStockExistance = favRepo.findBySymbolAndExchange(fstock.getSymbol(), fstock.getExchange());
		System.out.println(favStockExistance);
	
		if(favStockExistance != null) {
			return null;
		}
		FavouriteStock obj = favRepo.save(fstock);
		return obj;
	}
	
	@Override
	public List<FavouriteStock> viewAllFavouriteStocks() {
		// TODO Auto-generated method stub
		List<FavouriteStock> objList = favRepo.findAll();
		return objList;
	}

	@Override
	public FavouriteStock viewFavouriteStockByFavId(Long favouriteId) {
		// TODO Auto-generated method stub
		FavouriteStock obj = favRepo.findById(favouriteId).get();
		return obj;
	}

	@Override
	public FavouriteStock DeleteFavouriteStockByFavId(Long favouriteId) {
		// TODO Auto-generated method stub
		FavouriteStock obj = favRepo.findById(favouriteId).get();
		favRepo.deleteById(favouriteId);
		return obj;
	}
	
	
	@Override
	public FavouriteStock addStockForUsername(FavouriteStock fstock, String username) {
	    // Check if the stock exists for any user
	    FavouriteStock favStockExistance = favRepo.findBySymbolAndExchange(fstock.getSymbol(), fstock.getExchange());

	    // Check if the stock exists for the provided user
	    FavouriteStock favStockExistanceForUser = favRepo.findBySymbolAndExchangeAndUsername(fstock.getSymbol(), fstock.getExchange(), username);

	    // If the stock exists for any user or for the provided user, return null
	    if (favStockExistance != null && favStockExistanceForUser != null) {
	        return null;
	    }

	    // If the stock exists for any user but not for the provided user, update the username and save the record
	    if (favStockExistance != null && favStockExistanceForUser == null) {
	        fstock.setUsername(username);
	        FavouriteStock savedStock = favRepo.save(fstock);
	        return savedStock;
	    }

	    // If the stock does not exist, save it for the provided user
	    if (favStockExistance == null) {
	        fstock.setUsername(username);
	        FavouriteStock savedStock = favRepo.save(fstock);
	        return savedStock;
	    }

	    return null; // Default case: return null (for any unforeseen conditions)
	}
	@Override
	public List<FavouriteStock> viewAllFavouriteStocksByUsername(String username) {
        List<FavouriteStock> user = favRepo.findByUsername(username);

        if (user == null) {
            // Handle case where user doesn't exist
            return Collections.emptyList(); // or return null or throw an exception
        }

        return user;
    }
}


	
//	@Override
//	public FavouriteStock addStockForUsername(FavouriteStock fstock, String username) {
//		FavouriteStock favStockExistance = favRepo.findBySymbolAndExchange(fstock.getSymbol(), fstock.getExchange());
//		FavouriteStock favStockExistance1 = favRepo.findBySymbolAndExchangeAndUsername(fstock.getSymbol(), fstock.getExchange(), fstock.getUsername());
//		System.out.println(favStockExistance1);
//	
////		if(favStockExistance != null || favStockExistance1 != null) {
////			return null;
////		}
//		
//		 // If the stock exists but not for this user, update the username and save the record
//	    if (favStockExistance1 != null || favStockExistance != null) {
//	    	return null;
//	    }
//	    else {
//	    	fstock.setUsername(username);
//	        FavouriteStock savedStock = favRepo.save(fstock);
//	        return savedStock;
//	    }
//	    	
//	    
////		fstock.setUsername(username);
////		FavouriteStock obj = favRepo.save(fstock);
////		return obj;
//    }
	
	
//	@Override
//	public FavouriteStock addStockForUsername(FavouriteStock fstock, String username) {
//        User user = userRepo.findByUsername(username);
//        System.out.println("user not exist");
//        if (user == null) {
//            return null;
//        }
//        System.out.println("user not exist");
//        System.out.println(user.get());
//
//        
//
//        FavouriteStock existingStock = favRepo.findBySymbolAndExchangeAndUser(fstock.getSymbol(), fstock.getExchange(), user);
//
//        System.out.println("stock already exist");
//        if (existingStock != null) {
//            // Stock already exists for this user
//            return null;
//        }
//        
//        System.out.println(existingStock);
//
//        fstock.setUser(user);
//        
//        FavouriteStock savedStock = favRepo.save(fstock);
//        
//        //savedStock.setUser(user.get());
//        //savedStock.setUser(user.setUsername(username));
//        return savedStock;
//    }
//	
	
	
//	public boolean deleteFavoriteStockByUsernameAndFavStockId(String username, Long stockId) {
//        User user = userRepo.findByUsername(username);
//
//        if (user == null) {
//            // Handle case where user doesn't exist
//            return false;
//        }
//
//        Optional<FavouriteStock> favoriteStock = favRepo.findById(stockId);
//
////        if (favoriteStock.isEmpty() || !favoriteStock.get().getUser().equals(user)) {
////            // Handle case where stock doesn't exist or it doesn't belong to the user
////            return false;
////        }
//
//        favRepo.delete(favoriteStock.get());
//        return true;
//    }

//	@Override
//	public User saveUserDetails(User user) {
//		// TODO Auto-generated method stub
//		User userObj = userRepo.findByUsername(user.getUsername());
//		if (userObj != null) {
//			//throw new UserAlreadyExistException("User Already Exist in the data base");
//			return null;
//		} else {
//			userRepo.save(user);
//			return user;
//		}
//	}

//	@Override
//	public List<FavouriteStock> viewAllFavouriteStocksByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<FavouriteStock> viewAllFavouriteStocksByUsername(String username) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

//}
