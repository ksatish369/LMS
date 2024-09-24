package com.te.lms.dto;

import com.te.lms.enums.EducationType;

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
public class EducationDTO {

	private Double percentage;
	private String instituteName;
	private Integer yearOfPassing;
	private String specialization;
	private String universityName;
	private String state;

	private EducationType educationType;

}
