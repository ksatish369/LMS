package com.te.lms.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.te.lms.enums.MockNumber;

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
public class MockDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mockId;
	private String batchId;
	private MockNumber mockNumber;
	private String technologyName;
	private LocalDate date;
	private LocalTime time;
	private String panel;
	
}
