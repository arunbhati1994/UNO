package com.uno.exceptions;

public class IllegalCardThrownException extends Exception{
//we will throw this exception when card thrown is :
	//1)not same color
	//2)not same number
	//3)not an wildcard
	public IllegalCardThrownException() {
		super();
	}


	public IllegalCardThrownException(String message) {
		super(message);
	}

	
	
}
