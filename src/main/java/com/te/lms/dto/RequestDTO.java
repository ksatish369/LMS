package com.te.lms.dto;

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
public class RequestDTO {

	private Integer requestNumber;
	private String employeeId;
	private String employeeName;
	private Integer yearOfPassout;
	private Double percentage;
	private Double YearsOfExperience;
	private Long contactNumber;
	
	
}
