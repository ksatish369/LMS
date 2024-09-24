package com.te.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.ApproveDTO;
import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.MentorDTO;
import com.te.lms.dto.RequestDTO;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.AdminService;
import com.te.lms.service.MentorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/admin")
public class AdminController {

	private final AdminService adminService;
	private final MentorService mentorService;

	@PostMapping("/batch")
	public ResponseEntity<SuccessResponse<String>> addBatch(@RequestBody BatchDTO batchDTO) {
		return ResponseEntity.<SuccessResponse<String>>status(HttpStatus.CREATED).body(SuccessResponse.<String>builder()
				.data(adminService.addBatch(batchDTO)).message("Batch Successfully Added!!").build());
	}

	@PatchMapping("/batch")
	public ResponseEntity<SuccessResponse<String>> updateBatch(@RequestBody BatchDTO batchDTO) {
		return ResponseEntity.<SuccessResponse<String>>ok(SuccessResponse.<String>builder()
				.data(adminService.updateBatch(batchDTO)).message("Batch Successfully Updated!!").build());
	}
	
	@DeleteMapping("/batch")
	public ResponseEntity<SuccessResponse<String>> deleteBatch(@RequestParam String batchId) {
		return ResponseEntity.accepted().body(SuccessResponse.<String>builder().data(adminService.deleteBatch(batchId)).message("Batch with id : "+batchId+" deleted successfully!!").build());
	}

	@PostMapping("/mentor")
	public ResponseEntity<SuccessResponse<String>> addMentor(@RequestBody MentorDTO mentorDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<String>builder().data(mentorService.addMentor(mentorDTO)).message("Mentor has been added sucessfully!!").build());
	}
	
	@PatchMapping("/mentor")
	public ResponseEntity<SuccessResponse<String>> updateMentor(@RequestBody MentorDTO mentorDTO) {
		return ResponseEntity.ok(SuccessResponse.<String>builder().data(mentorService.updateMentor(mentorDTO)).message("Mentor has been updated sucessfully!!").build());
	}
	
	@DeleteMapping("/mentor")
	public ResponseEntity<SuccessResponse<String>> deleteMentor(@RequestParam String mentorId) {
		return ResponseEntity.accepted().body(SuccessResponse.<String>builder().data(mentorService.deleteMentor(mentorId)).message("Mentor with id : "+mentorId+" deleted successfully!!").build());
	}
	
	@GetMapping("/requests")
	public ResponseEntity<SuccessResponse<List<RequestDTO>>> getRequests() {
		return ResponseEntity.<SuccessResponse<List<RequestDTO>>>accepted().body(
				SuccessResponse.<List<RequestDTO>>builder().data(adminService.getRequests()).message("Requests have been fetched!!").build());
	}

	@GetMapping("/enum/tech")
	public ResponseEntity<SuccessResponse<List<String>>> getTechnologies() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted().body(
				SuccessResponse.<List<String>>builder().data(adminService.getTechnologies()).message("Technologies available have been fetched!!").build());
	}

	@GetMapping("/enum/mentor-names")
	public ResponseEntity<SuccessResponse<List<String>>> getMentorNames() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted().body(
				SuccessResponse.<List<String>>builder().data(adminService.getMentorNames()).message("Mentor names available have been fetched!!").build());
	}

	@GetMapping("/enum/batch-ids")
	public ResponseEntity<SuccessResponse<List<String>>> getBatchIds() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted().body(
				SuccessResponse.<List<String>>builder().data(adminService.getBatchIds()).message("BatchIds available have been fetched!!").build());
	}

	@GetMapping("/enum/batch-names")
	public ResponseEntity<SuccessResponse<List<String>>> getBatchNames() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted().body(
				SuccessResponse.<List<String>>builder().data(adminService.getBatchNames()).message("Batch Names available have been fetched!!").build());
	}

	@PatchMapping("/approve-employee")
	public ResponseEntity<SuccessResponse<String>> approveEmployee(@RequestBody ApproveDTO approveDTO) {
		return ResponseEntity.<SuccessResponse<String>>ok().body(SuccessResponse.<String>builder()
				.data(adminService.approveEmployee(approveDTO)).message("Employee has been approved!!").build());
	}

	@PatchMapping("/reject-employee")
	public ResponseEntity<SuccessResponse<String>> rejectEmployee(@RequestBody ApproveDTO approveDTO) {
		return ResponseEntity.<SuccessResponse<String>>ok().body(SuccessResponse.<String>builder()
				.data(adminService.rejectEmployee(approveDTO)).message("Employee has been rejected!!").build());
	}

}
