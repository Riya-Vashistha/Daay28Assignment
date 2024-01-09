package com.stocklisting.favourite.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//public class User {
//	
//	
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    
//    @Id
//	@Column(unique = true)
//	private String username;
//
//    // Define relationship with FavoriteStock entity
//   // @OneToMany(mappedBy = "user")
//    private List<FavouriteStock> favoriteStocks;
//    
//    public User() {
//    	
//    }
//
//}
