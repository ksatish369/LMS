package com.te.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.AttendanceDTO;
import com.te.lms.dto.BatchAttendanceDTO;
import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.EmployeeStatusDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.dto.MockRatingDTO;
import com.te.lms.dto.PasswordDTO;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.MentorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/mentor")
public class MentorController {

	private final MentorService mentorService;
	
	@PostMapping("/mock")
	public ResponseEntity<SuccessResponse<String>> createMock(@RequestBody MockDTO mockDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<String>builder().data(mentorService.addMock(mockDTO)).message("Mock for batchId : "+mockDTO.getBatchId()+" has been created!!").build()); 
	}
	
	@PatchMapping("/mock")
	public ResponseEntity<SuccessResponse<String>> giveMockRating(@RequestBody MockRatingDTO mockDTO) {
		return ResponseEntity.ok(SuccessResponse.<String>builder().data(mentorService.giveMockRating(mockDTO)).message("MockRating for : "+mockDTO.getEmployeeId()+" has been submitted!!").build()); 
	}
	
	@GetMapping("/batches")
	public ResponseEntity<SuccessResponse<List<BatchDTO>>> getBatches(@RequestParam String mentorId) {
		return ResponseEntity.accepted().body(SuccessResponse.<List<BatchDTO>>builder().data(mentorService.getBatches(mentorId)).message("Batches have been fetched!!").build()); 
	}
	
	@GetMapping("/employees")
	public ResponseEntity<SuccessResponse<List<EmployeeDTO>>> getEmployees(@RequestParam String batchName) {
		return ResponseEntity.accepted().body(SuccessResponse.<List<EmployeeDTO>>builder().data(mentorService.getEmployees(batchName)).message("Employees have been fetched!!").build()); 
	}
	
	@GetMapping("/attendance-dto")
	public ResponseEntity<SuccessResponse<List<AttendanceDTO>>> createAttendance(@RequestParam String batchId) {
		return ResponseEntity.accepted().body(SuccessResponse.<List<AttendanceDTO>>builder().data(mentorService.createAttendance(batchId)).message("Attendance DTO created for front End!!").build()); 
	}
	
	@PutMapping("/attendance")
	public ResponseEntity<SuccessResponse<String>> submitAttendance(@RequestBody BatchAttendanceDTO batchAttendanceDTO) {
		return ResponseEntity.ok(SuccessResponse.<String>builder().data(mentorService.submitAttendance(batchAttendanceDTO)).message("Attendance submitted!!").build()); 
	}
	
	@PatchMapping("/employee-status")
	public ResponseEntity<SuccessResponse<String>> changeEmployeeStatus(@RequestBody EmployeeStatusDTO employeeStatusDTO) {
		return ResponseEntity.ok(SuccessResponse.<String>builder().data(mentorService.changeEmployeeStatus(employeeStatusDTO)).message("Employee Status changed!!!!").build()); 
	}
	
	@PatchMapping("/password")
	public ResponseEntity<SuccessResponse<String>> changePassword(@RequestBody PasswordDTO passwordDTO) {
		return ResponseEntity.ok(SuccessResponse.<String>builder().data(mentorService.changePassword(passwordDTO)).message("Password has been successfully reset!!").build()); 
	}
}

