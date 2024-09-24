package com.te.lms.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.multipart.MultipartFile;

import com.te.lms.dto.AddressDTO;
import com.te.lms.dto.AttendanceDTO;
import com.te.lms.dto.BankDTO;
import com.te.lms.dto.ContactDTO;
import com.te.lms.dto.EducationDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.ExperienceDTO;
import com.te.lms.dto.MockDetailsDTO;
import com.te.lms.dto.PasswordDTO;
import com.te.lms.dto.PrimaryInfoDTO;
import com.te.lms.dto.SecondaryInfoDTO;
import com.te.lms.dto.TechnicalSkillDTO;
import com.te.lms.response.SuccessResponse;
import com.te.lms.service.EmployeeService;
import com.te.lms.service.ExcelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/employee")
public class EmployeeController {

	private final EmployeeService employeeService;
	private final ExcelService excelService;

//	API's for next buttons

	@PostMapping("/")
	public ResponseEntity<SuccessResponse<String>> savePrimaryInfo(@RequestBody @Valid PrimaryInfoDTO primaryInfoDTO) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SuccessResponse.<String>builder().data(employeeService.savePrimaryInfo(primaryInfoDTO)).build());

	}

	@PutMapping("/secondary")
	public ResponseEntity<SuccessResponse<String>> updateSecondaryInfo(@RequestBody SecondaryInfoDTO secondaryInfoDTO,
			@RequestParam String employeeId) {
		return ResponseEntity.ok(SuccessResponse.<String>builder()
				.data(employeeService.updateSecondaryInfo(secondaryInfoDTO, employeeId)).build());

	}

	@PutMapping("/education")
	public ResponseEntity<SuccessResponse<String>> updateEduction(@RequestBody List<EducationDTO> educationDTOs,
			@RequestParam String employeeId) {
		return ResponseEntity.ok(SuccessResponse.<String>builder()
				.data(employeeService.updateEductaionInfo(educationDTOs, employeeId)).build());
	}

	@PutMapping("/address")
	public ResponseEntity<SuccessResponse<String>> updateAddress(@RequestBody List<AddressDTO> addressDTOs,
			@RequestParam String employeeId) {
		return ResponseEntity.ok(SuccessResponse.<String>builder()
				.data(employeeService.updateAddressInfo(addressDTOs, employeeId)).build());
	}

	@PutMapping("/bank")
	public ResponseEntity<SuccessResponse<String>> updateBankDetail(@RequestBody BankDTO bankDTO,
			@RequestParam String employeeId) {
		return ResponseEntity.ok(
				SuccessResponse.<String>builder().data(employeeService.updateBankDetail(bankDTO, employeeId)).build());
	}

	@PutMapping("/tech")
	public ResponseEntity<SuccessResponse<String>> updateTechSkills(
			@RequestBody List<TechnicalSkillDTO> technicalSkillDTOs, @RequestParam String employeeId) {
		return ResponseEntity.ok(SuccessResponse.<String>builder()
				.data(employeeService.updateTechSkills(technicalSkillDTOs, employeeId)).build());
	}

	@PutMapping("/experience")
	public ResponseEntity<SuccessResponse<String>> updateExperience(@RequestBody List<ExperienceDTO> experienceDTOs,
			@RequestParam String employeeId) {
		return ResponseEntity.ok(SuccessResponse.<String>builder()
				.data(employeeService.updateExperience(experienceDTOs, employeeId)).build());
	}

	@PutMapping("/submit")
	public ResponseEntity<SuccessResponse<String>> submit(@RequestParam String employeeId,
			@RequestBody List<ContactDTO> contactDTOs) {
		employeeService.updateContact(contactDTOs, employeeId);
		return ResponseEntity.<SuccessResponse<String>>ok()
				.body(SuccessResponse.<String>builder().data(employeeService.submitRequest(employeeId)).build());
	}

//	API's for next button ends

//	"Get" API's for previous buttons
	@GetMapping("/primary")
	public ResponseEntity<SuccessResponse<PrimaryInfoDTO>> getPrimaryInfo(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<PrimaryInfoDTO>>accepted().body(
				SuccessResponse.<PrimaryInfoDTO>builder().data(employeeService.getPrimaryInfoDTO(employeeId)).build());
	}

	@GetMapping("/secondary")
	public ResponseEntity<SuccessResponse<SecondaryInfoDTO>> getSecondaryInfo(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<SecondaryInfoDTO>>accepted().body(SuccessResponse
				.<SecondaryInfoDTO>builder().data(employeeService.getSecondaryInfoDTO(employeeId)).build());
	}

	@GetMapping("/education")
	public ResponseEntity<SuccessResponse<List<EducationDTO>>> getEducation(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<List<EducationDTO>>>accepted().body(SuccessResponse
				.<List<EducationDTO>>builder().data(employeeService.getEducationDTO(employeeId)).build());
	}

	@GetMapping("/address")
	public ResponseEntity<SuccessResponse<List<AddressDTO>>> getAddress(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<List<AddressDTO>>>accepted().body(
				SuccessResponse.<List<AddressDTO>>builder().data(employeeService.getAddressDTO(employeeId)).build());
	}

	@GetMapping("/bank")
	public ResponseEntity<SuccessResponse<BankDTO>> getBank(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<BankDTO>>accepted()
				.body(SuccessResponse.<BankDTO>builder().data(employeeService.getBankDTO(employeeId)).build());

	}

	@GetMapping("/experience")
	public ResponseEntity<SuccessResponse<List<ExperienceDTO>>> getExperience(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<List<ExperienceDTO>>>accepted().body(SuccessResponse
				.<List<ExperienceDTO>>builder().data(employeeService.getExperienceDTO(employeeId)).build());
	}

	@GetMapping("/tech")
	public ResponseEntity<SuccessResponse<List<TechnicalSkillDTO>>> getTechnicalSkills(
			@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<List<TechnicalSkillDTO>>>accepted().body(SuccessResponse
				.<List<TechnicalSkillDTO>>builder().data(employeeService.getTechnicalSkillDTO(employeeId)).build());
	}
//	previous API's end

	@GetMapping("/")
	public ResponseEntity<SuccessResponse<EmployeeDTO>> getEmployee(@RequestParam String employeeId) {
		return ResponseEntity.accepted()
				.body(SuccessResponse.<EmployeeDTO>builder().data(employeeService.getEmployee(employeeId)).build());
	}

	@GetMapping("/attendance")
	public ResponseEntity<SuccessResponse<Map<String, Integer>>> getAttendanceRatio(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<Map<String, Integer>>>accepted().body(SuccessResponse
				.<Map<String, Integer>>builder().data(employeeService.getAttendanceRatio(employeeId)).build());
	}

	@GetMapping("/attendance/details")
	public ResponseEntity<SuccessResponse<List<AttendanceDTO>>> getAttendance(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<List<AttendanceDTO>>>accepted().body(SuccessResponse
				.<List<AttendanceDTO>>builder().data(employeeService.getAttendanceDTO(employeeId)).build());
	}

	@GetMapping("/mock")
	public ResponseEntity<SuccessResponse<List<MockDetailsDTO>>> getMock(@RequestParam String employeeId) {
		return ResponseEntity.<SuccessResponse<List<MockDetailsDTO>>>accepted().body(
				SuccessResponse.<List<MockDetailsDTO>>builder().data(employeeService.getMockDTO(employeeId)).build());
	}

//	API for creating attendance

//	Get API's for Drop Downs

	@GetMapping("/enum/designation")
	public ResponseEntity<SuccessResponse<List<String>>> getDesignation() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getDesignation()).build());
	}

	@GetMapping("/enum/gender")
	public ResponseEntity<SuccessResponse<List<String>>> getGender() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getGender()).build());
	}

	@GetMapping("/enum/emp-status")
	public ResponseEntity<SuccessResponse<List<String>>> getEmployeeStatus() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getEmployeeStatus()).build());
	}

	@GetMapping("/enum/marital-status")
	public ResponseEntity<SuccessResponse<List<String>>> getMaritalStatus() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getMaritalStatus()).build());
	}

	@GetMapping("/enum/edu-type")
	public ResponseEntity<SuccessResponse<List<String>>> getEducationType() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getEducationType()).build());
	}

	@GetMapping("/enum/addr-type")
	public ResponseEntity<SuccessResponse<List<String>>> getAddressType() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getAddressType()).build());
	}

	@GetMapping("/enum/cont-type")
	public ResponseEntity<SuccessResponse<List<String>>> getContactType() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getContactType()).build());
	}

	@GetMapping("/enum/acc-type")
	public ResponseEntity<SuccessResponse<List<String>>> getAccountType() {
		return ResponseEntity.<SuccessResponse<List<String>>>accepted()
				.body(SuccessResponse.<List<String>>builder().data(employeeService.getAccountType()).build());
	}

//  End of Get API's for Drop Downs

	@PatchMapping("/password")
	public ResponseEntity<SuccessResponse<String>> changePassword(@RequestBody PasswordDTO passwordDTO) {
		return ResponseEntity.ok(SuccessResponse.<String>builder().data(employeeService.changePassword(passwordDTO))
				.message("Password has been successfully reset!!").build());
	}

	@GetMapping("/download/all-employees")
	public void getAllEmployees(HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "inline; filename=employees_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		excelService.downloadEmployeeData(response);
	}
	
	@PostMapping(path = "/upload-employees-excel")
	public ResponseEntity<SuccessResponse<String>> addDataFromExcelToDatabase(@RequestParam MultipartFile file) throws IOException {
		
		if(excelService.checkExcelFromat(file)) {
			if(employeeService.addDataFromExcelToDatabase(file.getInputStream())) {
				return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<String>builder().data("Success").message("Data added succesfully to database from excel").build());
			}
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.<String>builder().data("Fail").message("Failed to add the data").build());
	}
}
	
	
	

