package com.te.lms.dto;

import com.te.lms.enums.MockRating;
import com.te.lms.enums.MockType;

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
public class MockRatingDTO {

	private String employeeId;
	private Double practicalKnowledge;
	private Double theoreticalKnowledge;
	private MockRating overallFeedback;
	private String detailedFeedBack;
	private String technologyName;
	private MockType mockType;
	
}
