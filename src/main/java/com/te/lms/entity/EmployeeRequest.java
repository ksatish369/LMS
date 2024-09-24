package com.te.lms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "employee_request")
public class EmployeeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requestId;
	private String employeeId;
	private String employeeName;
	private String reasonForRejection;
	private boolean isApproved;
	private boolean actionTaken;
	private Integer yearOfPassout;
	private Double yearsOfExperience;
	private Long contactNumber;
	private Double percentage;
	
}
