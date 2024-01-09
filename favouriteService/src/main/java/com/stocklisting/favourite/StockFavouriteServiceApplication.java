package com.stocklisting.favourite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.stocklisting.favourite.filter.FavStockFilter;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.stocklisting.favourite", "com.stocklisting.favourite.aop"})
public class StockFavouriteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockFavouriteServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getfilter()
	{
		FilterRegistrationBean fbean=new FilterRegistrationBean();
		fbean.setFilter(new FavStockFilter());
		fbean.addUrlPatterns("/favourite/*");
		return fbean;
		
	}

}
