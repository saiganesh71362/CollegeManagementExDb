package com.acrunetit.college.globalexceptionhandle;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandle {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleStudentNotFoundException(
			StudentNotFoundException studentNotFoundException, WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), studentNotFoundException.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(CollegeNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handelCollegeNotFoundException(
			CollegeNotFoundException collegeNotFoundException, WebRequest webRequest) {

		ExceptionMessage message = new ExceptionMessage(new Date(), collegeNotFoundException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BranchNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleBranchNotFoundException(
			BranchNotFoundException branchNotFoundException, WebRequest webRequest) {
		ExceptionMessage message = new ExceptionMessage(new Date(), branchNotFoundException.getMessage(),
				webRequest.getDescription(false));
		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}
