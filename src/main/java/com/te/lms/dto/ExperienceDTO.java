package com.te.lms.dto;

import java.time.LocalDate;

import com.te.lms.enums.Designation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExperienceDTO {

	private String companyName;
	private LocalDate dateOfJoining;
	private Double yearOfExperience;
	private LocalDate dateOfRelieving;
	private String location;

	private Designation designation;
}
