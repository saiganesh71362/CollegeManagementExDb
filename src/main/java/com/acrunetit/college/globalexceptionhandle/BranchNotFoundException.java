package com.acrunetit.college.globalexceptionhandle;

public class BranchNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BranchNotFoundException(String message) {
		super(message);
	}
}
