package com.hsbc.downstream.exceptionHandlers;

public class DownStreamDtoException extends RuntimeException {

	public DownStreamDtoException(int id) {
	    super("Could not find DownStreamDto " + id);
	  }
	}
