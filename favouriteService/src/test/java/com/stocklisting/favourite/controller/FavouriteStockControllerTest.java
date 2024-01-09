package com.stocklisting.favourite.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocklisting.favourite.model.FavouriteStock;
import com.stocklisting.favourite.service.FavouriteStockService;

@ExtendWith(MockitoExtension.class)
public class FavouriteStockControllerTest {

	private MockMvc mockMvc;
	@Mock
	FavouriteStockService favStockService;

	@InjectMocks
	private FavouriteStockController favStockController;

	private FavouriteStock favStock;
	private List<FavouriteStock> favStockList;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(favStockController).build();
		favStock = new FavouriteStock();
		favStock.setFavouriteId(1L);
		favStock.setName("Nippon India Mutual Fund");
		favStock.setCurrency("INR");
		favStock.setCountry("INDIA");
		favStock.setExchange("BSE");
		favStock.setType("Common Stock");
		favStockList = new ArrayList<>();
		favStockList.add(favStock);
	}

	@AfterEach
	void tearDown() {
		favStock = null;
	}

	@Test
	void givenPostMappingUrlThenShouldReturnTheAddedFavStock() throws Exception {
		when(favStockService.addFavouriteStock(any())).thenReturn(favStock);
		mockMvc.perform(post("/favourite/addStock").contentType(MediaType.APPLICATION_JSON).content(asJsonString(favStock)))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
		verify(favStockService).addFavouriteStock(any());
	}

	@Test
	void givenGetMappingUrlThenShouldReturnListOfAllFavStocks() throws Exception {
		when(favStockService.viewAllFavouriteStocks()).thenReturn(favStockList);
		mockMvc.perform(MockMvcRequestBuilders.get("/favourite/viewAllStock").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(favStock))).andDo(MockMvcResultHandlers.print());
		verify(favStockService).viewAllFavouriteStocks();
		verify(favStockService, times(1)).viewAllFavouriteStocks();

	}

	@Test
	void givenFavIdThenShouldReturnRespectiveFavStock() throws Exception {
		when(favStockService.viewFavouriteStockByFavId(favStock.getFavouriteId())).thenReturn(favStock);
		mockMvc.perform(get("/favourite/stock/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(favStock)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		verify(favStockService).viewFavouriteStockByFavId(favStock.getFavouriteId());
		verify(favStockService, times(1)).viewFavouriteStockByFavId(favStock.getFavouriteId());
	}

	@Test
	public void givenDeleteMappingUrlAndFavIdThenShouldReturnDeletedFavStock() throws Exception {
		when(favStockService.DeleteFavouriteStockByFavId(anyLong())).thenReturn(favStock);
		mockMvc.perform(delete("/favourite/deleteStock/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(favStock)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
		verify(favStockService).DeleteFavouriteStockByFavId(anyLong());
		verify(favStockService, times(1)).DeleteFavouriteStockByFavId(anyLong());
	}


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
