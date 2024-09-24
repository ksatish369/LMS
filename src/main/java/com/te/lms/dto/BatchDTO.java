package com.te.lms.dto;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.te.lms.enums.BatchStatus;
import com.te.lms.enums.BatchTechnology;

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
public class BatchDTO {

	private String batchId;
	private String batchName;
	private LocalDate startDate;
	private LocalDate endDate;
	private String mentorName;
	private BatchTechnology technology;
	private BatchStatus batchStatus;
}
