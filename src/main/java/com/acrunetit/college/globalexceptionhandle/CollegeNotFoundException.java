package com.acrunetit.college.globalexceptionhandle;

public class CollegeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CollegeNotFoundException(String message) {
		super(message);
	}

}
