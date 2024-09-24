package com.te.lms.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.te.lms.enums.Designation;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PrimaryInfoDTO {

	private String employeeId;
	@NotEmpty(message = "Name must not be empty")
	@NotNull
	private String employeeName;
	@Email(message = "Email Invalid")
	private String emailId;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoin;
	private String bloodGroup;
	private String nationality;
	private Double salary;

	private Gender gender;
	private EmployeeStatus employeeStatus;
	private Designation designation;

}
