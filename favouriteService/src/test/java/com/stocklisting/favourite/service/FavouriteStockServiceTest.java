package com.stocklisting.favourite.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.stocklisting.favourite.model.FavouriteStock;
import com.stocklisting.favourite.repository.FavouriteStockRepository;
import com.stocklisting.favourite.service.FavouriteStockServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FavouriteStockServiceTest {

    @Mock
    private FavouriteStockRepository favRepo;

    @InjectMocks
    private FavouriteStockServiceImpl favService;

    @Test
    public void testAddFavouriteStock() {
        // Mocking data
        FavouriteStock fstock = new FavouriteStock(/* Set necessary parameters */);
        FavouriteStock existingStock = new FavouriteStock(/* Set existing stock parameters */);

        when(favRepo.findBySymbolAndExchange(fstock.getSymbol(), fstock.getExchange())).thenReturn(existingStock);

        // Test the service method
        FavouriteStock result = favService.addFavouriteStock(fstock);

        assertNull(result); // Verify that null is returned for existing stock
        verify(favRepo, times(1)).findBySymbolAndExchange(fstock.getSymbol(), fstock.getExchange());
        verify(favRepo, times(0)).save(fstock); // Ensure that save is not called for existing stock
    }

    // Similarly, write test methods for other service methods such as viewAllFavouriteStocks, viewFavouriteStockByFavId, etc.

    @Test
    public void testViewAllFavouriteStocksByUsername() {
        // Mocking data
        String username = "testUser";
        List<FavouriteStock> mockUserList = Collections.singletonList(new FavouriteStock(/* Set necessary parameters */));

        when(favRepo.findByUsername(username)).thenReturn(mockUserList);

        // Test the service method
        List<FavouriteStock> result = favService.viewAllFavouriteStocksByUsername(username);

        assertEquals(mockUserList, result);
        verify(favRepo, times(1)).findByUsername(username);
    }
}





//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.stocklisting.favourite.model.FavouriteStock;
//
//import com.stocklisting.favourite.repository.FavouriteStockRepository;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class FavouriteStockServiceTest {
//    @Mock
//    private FavouriteStockRepository favRepository;
//    @Autowired
//    @InjectMocks
//    private FavouriteStockServiceImpl favService;
//    private FavouriteStock favStock1;
//    private FavouriteStock favStock2;
//    private FavouriteStock favStock3;
//    private List<FavouriteStock> favStockList;
//
//    @BeforeEach
//    public void setUp() {
////    	User user = new User();
////    	user.setId(1L);
//    	
//        favStockList = new ArrayList<>();
//        favStock1 = new FavouriteStock(1L,"000","Rashmi", "Greenvolt - Energias Renovaveis SA", "EUR", "XBER", "Germany", "Common Stock");
//        favStock2 = new FavouriteStock(2L,"001","Riya", "Nippon India Mutual Fund", "INR", "BSE", "India", "Common Stock");
//        favStock3 = new FavouriteStock(3L,"002", "Priya","Greenvolt - Energias Renovaveis SA", "EUR", "XBER", "Dubai", "Common Stock");
//        favStockList.add(favStock1);
//        favStockList.add(favStock2);
//    }
//
//    @AfterEach
//    public void tearDown() {
//    	favStock1 = favStock2 = favStock3 = null;
//        favStockList = null;
//    }
//
//    @Test
//    void givenFavStockToSaveThenShouldReturnAddedFavStock() {
//        when(favRepository.save(any())).thenReturn(favStock1);
//        assertEquals(favStock1, favService.addFavouriteStock(favStock1));
//        verify(favRepository, times(1)).save(any());
//    }
//
//    @Test
//    void givenCallToGetAllFavStockThenShouldReturnListOfAllFavStocks() {
//    	when(favRepository.findAll()).thenReturn(favStockList);
//    	favService.addFavouriteStock(favStock1);
//    	favService.addFavouriteStock(favStock2);
//        favService.viewAllFavouriteStocks();
//        verify(favRepository, times(1)).findAll();
//    }
//
//    @Test
//    void givenFavIdThenShouldReturnFavStockWithThatFavId() {
//        when(favRepository.findById(anyLong())).thenReturn(Optional.of(favStock1));
//        favService.addFavouriteStock(favStock1);
//        favService.viewFavouriteStockByFavId(favStock1.getFavouriteId());
//        verify(favRepository, times(1)).findById(anyLong());
//
//    }
//
//
//    @Test
//    void givenFavouriteStockToDeleteThenShouldReturnDeletedFavouriteStock() {
//    	when(favRepository.findById(anyLong())).thenReturn(Optional.of(favStock1));
//        favService.DeleteFavouriteStockByFavId(1L);
//        verify(favRepository, times(1)).findById(anyLong());
//    }
//
//
//}