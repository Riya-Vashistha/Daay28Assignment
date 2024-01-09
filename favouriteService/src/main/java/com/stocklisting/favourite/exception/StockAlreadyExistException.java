package com.stocklisting.favourite.exception;

public class StockAlreadyExistException extends RuntimeException{

	public StockAlreadyExistException(String msg) {
		super(msg);
	}
}
