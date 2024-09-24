package com.te.lms.exception;

public class AttendanceAlreadyAddedException extends RuntimeException {

	public AttendanceAlreadyAddedException(String msg) {
		super(msg);
	}
}
