package com.te.lms.exception.handler;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.lms.exception.AttendanceAlreadyAddedException;
import com.te.lms.exception.AttendanceNotAvailableException;
import com.te.lms.exception.BatchIdNotFoundException;
import com.te.lms.exception.EducationNotFoundException;
import com.te.lms.exception.MentorIdAlreadyPresentException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.exception.MockNotFoundException;
import com.te.lms.exception.PasswordInCorrectException;
import com.te.lms.exception.PasswordMismatchException;
import com.te.lms.exception.UserIdNotAvailable;
import com.te.lms.response.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(UserIdNotAvailable.class)
	public ErrorResponse userAlreadyPresent(UserIdNotAvailable userIdNotAvailable) {
		return ErrorResponse.builder().message(userIdNotAvailable.getMessage()).build();
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(AttendanceAlreadyAddedException.class)
	public ErrorResponse attendanceAlreadyAdded(AttendanceAlreadyAddedException attendanceAlreadyAddedException) {
		return ErrorResponse.builder().message(attendanceAlreadyAddedException.getMessage()).build();
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(AttendanceNotAvailableException.class)
	public ErrorResponse attendanceNotAvailable(AttendanceNotAvailableException attendanceNotAvailableException) {
		return ErrorResponse.builder().message(attendanceNotAvailableException.getMessage()).build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MentorIdAlreadyPresentException.class)
	public ErrorResponse mentorAlreadyAdded(MentorIdAlreadyPresentException mentorIdAlreadyPresentException) {
		return ErrorResponse.builder().message(mentorIdAlreadyPresentException.getMessage()).build();
	}
		
	@org.springframework.web.bind.annotation.ExceptionHandler(EducationNotFoundException.class)
	public ErrorResponse mentorAlreadyAdded(EducationNotFoundException educationNotFoundException) {
		return ErrorResponse.builder().message(educationNotFoundException.getMessage()).build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(BatchIdNotFoundException.class)
	public ErrorResponse mentorAlreadyAdded(BatchIdNotFoundException batchIdNotFoundException) {
		return ErrorResponse.builder().message(batchIdNotFoundException.getMessage()).build();
	}
	

	@org.springframework.web.bind.annotation.ExceptionHandler(MentorNotFoundException.class)
	public ErrorResponse mentorAlreadyAdded(MentorNotFoundException mentorNotFoundException) {
		return ErrorResponse.builder().message(mentorNotFoundException.getMessage()).build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(MockNotFoundException.class)
	public ErrorResponse mentorAlreadyAdded(MockNotFoundException mockNotFoundException) {
		return ErrorResponse.builder().message(mockNotFoundException.getMessage()).build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ErrorResponse emailUniqueException(SQLIntegrityConstraintViolationException sQLIntegrityConstraintViolationException) {
		return ErrorResponse.builder().message(sQLIntegrityConstraintViolationException.getMessage()).build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(PasswordInCorrectException.class)
	public ErrorResponse emailUniqueException(PasswordInCorrectException passwordInCorrectException) {
		return ErrorResponse.builder().message(passwordInCorrectException.getMessage()).build();
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(PasswordMismatchException.class)
	public ErrorResponse emailUniqueException(PasswordMismatchException passwordMismatchException) {
		return ErrorResponse.builder().message(passwordMismatchException.getMessage()).build();
	}
}
