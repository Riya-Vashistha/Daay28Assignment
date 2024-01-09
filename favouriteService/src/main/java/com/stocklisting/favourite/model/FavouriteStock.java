package com.stocklisting.favourite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class FavouriteStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long favouriteId;
	String username;
	String symbol;
	String name;
	String currency;
	String exchange;
	String country;
	String type;

//	@ManyToOne
//	@JoinColumn(name = "user")
//	private User user;

	public FavouriteStock() {

	}

}
