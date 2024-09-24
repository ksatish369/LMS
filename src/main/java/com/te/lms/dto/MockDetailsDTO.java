package com.te.lms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.te.lms.entity.Technology;
import com.te.lms.enums.MockNumber;
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
public class MockDetailsDTO {

	private Integer mockId;
	private LocalDate date;
	private LocalTime time;
	private String panel;
	private Double practicalKnowledge;
	private Double theoreticalKnowledge;
	private String detailedFeedBack;

	private MockNumber mockNumber;
	
	private Technology technology;
	
	private MockType mockType;
	
	private MockRating mockRating;
}
