package com.stocklisting.favourite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocklisting.favourite.model.FavouriteStock;


@Repository
public interface FavouriteStockRepository extends JpaRepository<FavouriteStock, Long>{
	
	FavouriteStock findBySymbolAndExchangeAndUsername(String symbol, String exchange, String username);
	FavouriteStock findBySymbolAndExchange(String symbol, String exchange);
	List<FavouriteStock> findByUsername(String username);
	FavouriteStock findBySymbolAndExchangeAndUsernameNot(String symbol, String exchange, String username);
	
	//FavouriteStock findBySymbolAndExchangeAndUser(String symbol, String exchange, User user);
	//List<FavouriteStock> findByUser(User user);
	

}
