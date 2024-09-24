package com.te.lms.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.lms.enums.Designation;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.enums.Gender;
import com.te.lms.enums.MaritalStatus;

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
public class SecondaryInfoDTO {

	private String panNumber;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private Long aadharNumber;
	private Long passportNumber;
	private MaritalStatus maritalStatus;
}
