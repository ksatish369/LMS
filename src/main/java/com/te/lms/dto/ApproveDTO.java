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
public class ApproveDTO {

	private String employeeId;
	private String batchId;
	private String batchName;
	private Integer requestNumber;
	private String reasonForRejection;
}
