package com.te.lms.dto;

import com.te.lms.enums.EducationType;
import com.te.lms.enums.EmployeeStatus;

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
public class EmployeeStatusDTO {

	private String employeeId;
	private String reasonForLeaving;
	private EmployeeStatus employeeStatus;
}
