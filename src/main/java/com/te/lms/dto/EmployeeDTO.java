package com.te.lms.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.te.lms.enums.Designation;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.enums.Gender;

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
public class EmployeeDTO {

	private String employeeId;
	private String employeeName;
	private String emailId;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoin;
	private String bloodGroup;
	private String nationality;
	private Double salary;

	private Gender gender;
	private EmployeeStatus employeeStatus;
	private Designation designation;
	
	private SecondaryInfoDTO secondaryInfoDTO = new SecondaryInfoDTO();
	private List<EducationDTO> educationDTOs = new ArrayList<EducationDTO>();
	private List<AddressDTO> addressDTOs = new ArrayList<AddressDTO>();
	private BankDTO bankDTO = new BankDTO();
	private List<TechnicalSkillDTO> technicalSkillDTOs = new ArrayList<TechnicalSkillDTO>();
	private List<ExperienceDTO> experienceDTOs = new ArrayList<ExperienceDTO>();
	private List<ContactDTO> contactDTOs = new ArrayList<ContactDTO>();
}
