package com.te.lms.utils;

import org.springframework.beans.BeanUtils;

import com.te.lms.dto.AddressDTO;
import com.te.lms.dto.AttendanceDTO;
import com.te.lms.dto.BankDTO;
import com.te.lms.dto.ContactDTO;
import com.te.lms.dto.EducationDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.ExperienceDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.dto.MockDetailsDTO;
import com.te.lms.dto.PrimaryInfoDTO;
import com.te.lms.dto.SecondaryInfoDTO;
import com.te.lms.dto.TechnicalSkillDTO;
import com.te.lms.entity.Address;
import com.te.lms.entity.Attendance;
import com.te.lms.entity.BankDetail;
import com.te.lms.entity.Contact;
import com.te.lms.entity.Education;
import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeSecondaryInfo;
import com.te.lms.entity.Experience;
import com.te.lms.entity.Mock;
import com.te.lms.entity.TechnicalSkill;

public class EmployeeUtils {

	public static Employee convertDtoToEntity(PrimaryInfoDTO primaryInfoDTO) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(primaryInfoDTO, employee);
		return employee;
	}

	public static EmployeeSecondaryInfo convertDtoToEntity(SecondaryInfoDTO secondaryInfoDTO) {
		EmployeeSecondaryInfo employeeSecondaryInfo = new EmployeeSecondaryInfo();
		BeanUtils.copyProperties(secondaryInfoDTO, employeeSecondaryInfo);
		return employeeSecondaryInfo;
	}

	public static Education convertDtoToEntity(EducationDTO educationDTO) {
		Education education = new Education();
		BeanUtils.copyProperties(educationDTO, education);
		return education;
	}

	public static Address convertDtoToEntity(AddressDTO addressDTO) {
		Address address = new Address();
		BeanUtils.copyProperties(addressDTO, address);
		return address;
	}

	public static BankDetail convertDtoToEntity(BankDTO bankDTO) {
		BankDetail bank = new BankDetail();
		BeanUtils.copyProperties(bankDTO, bank);
		return bank;
	}

	public static TechnicalSkill convertDtoToEntity(TechnicalSkillDTO technicalSkillDTO) {
		TechnicalSkill technicalSkill = new TechnicalSkill();
		BeanUtils.copyProperties(technicalSkillDTO, technicalSkill);
		return technicalSkill;
	}

	public static Experience convertDtoToEntity(ExperienceDTO experienceDTO) {
		return Experience.builder().companyName(experienceDTO.getCompanyName())
				.dateOfJoining(experienceDTO.getDateOfJoining()).dateOfRelieving(experienceDTO.getDateOfRelieving())
				.designation(experienceDTO.getDesignation()).yearOfExperience(experienceDTO.getYearOfExperience())
				.location(experienceDTO.getLocation()).build();
	}

	public static Contact convertDtoToEntity(ContactDTO contactDTO) {
		Contact contact = new Contact();
		BeanUtils.copyProperties(contactDTO, contact);
		return contact;
	}

	public static EmployeeDTO convertEntityToDto(Employee employee, boolean AllDetails) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		
		BeanUtils.copyProperties(employee, employeeDTO);
		if(employee.getEmployeeSecondaryInfo()!=null)
		BeanUtils.copyProperties(employee.getEmployeeSecondaryInfo(), employeeDTO.getSecondaryInfoDTO());
		if(employee.getBankDetail()!=null)
		BeanUtils.copyProperties(employee.getBankDetail(), employeeDTO.getBankDTO());
		employee.getAddressDetails().forEach((a) -> {
			AddressDTO addressDTO = new AddressDTO();
			BeanUtils.copyProperties(a, addressDTO);
			employeeDTO.getAddressDTOs().add(addressDTO);
		});
		employee.getEducationDetails().forEach((a) -> {
			EducationDTO educationDTO = new EducationDTO();
			BeanUtils.copyProperties(a, educationDTO);
			employeeDTO.getEducationDTOs().add(educationDTO);
		});
		employee.getTechnicalSkills().forEach((a) -> {
			TechnicalSkillDTO technicalSkillDTO = new TechnicalSkillDTO();
			BeanUtils.copyProperties(a, technicalSkillDTO);
			employeeDTO.getTechnicalSkillDTOs().add(technicalSkillDTO);
		});
		employee.getContacts().forEach((a) -> {
			ContactDTO contactDTO = new ContactDTO();
			BeanUtils.copyProperties(a, contactDTO);
			employeeDTO.getContactDTOs().add(contactDTO);
		});
		employee.getExperiences().forEach((a) -> {
			ExperienceDTO experienceDTO = new ExperienceDTO();
			BeanUtils.copyProperties(a, experienceDTO);
			employeeDTO.getExperienceDTOs().add(experienceDTO);
		});
		return employeeDTO;
	}

	public static PrimaryInfoDTO convertEntityToDto(Employee e) {
		PrimaryInfoDTO primaryInfoDTO = new PrimaryInfoDTO();
		BeanUtils.copyProperties(e, primaryInfoDTO);
		return primaryInfoDTO;
	}

	public static SecondaryInfoDTO convertEntityToDto(EmployeeSecondaryInfo employeeSecondaryInfo) {
		SecondaryInfoDTO secondaryInfoDTO = new SecondaryInfoDTO();
		BeanUtils.copyProperties(employeeSecondaryInfo, secondaryInfoDTO);
		return secondaryInfoDTO;
	}

	public static EducationDTO convertEntityToDto(Education education) {
		EducationDTO educationDTO = new EducationDTO();
		BeanUtils.copyProperties(education, educationDTO);
		return educationDTO;
	}

	public static AddressDTO convertEntityToDto(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		BeanUtils.copyProperties(address, addressDTO);
		return addressDTO;
	}

	public static BankDTO convertEntityToDto(BankDetail bankDetail) {
		BankDTO bankDTO = new BankDTO();
		BeanUtils.copyProperties(bankDetail, bankDTO);
		return bankDTO;
	}

	public static ExperienceDTO convertEntityToDto(Experience experience) {
		ExperienceDTO experienceDTO = new ExperienceDTO();
		BeanUtils.copyProperties(experience, experienceDTO);
		return experienceDTO;
	}

	public static TechnicalSkillDTO convertEntityToDto(TechnicalSkill technicalSkill) {
		TechnicalSkillDTO technicalSkillDTO = new TechnicalSkillDTO();
		BeanUtils.copyProperties(technicalSkill,technicalSkillDTO);
		return technicalSkillDTO;
	}

	public static AttendanceDTO convertEntityToDto(Attendance attendance) {
		AttendanceDTO attendanceDTO = new AttendanceDTO();
		BeanUtils.copyProperties(attendance, attendanceDTO);
		return attendanceDTO;
	}

	public static MockDTO convertEntityToDto(Mock mock) {
		MockDTO mockDTO = new MockDTO();
		BeanUtils.copyProperties(mock, mockDTO);
		return mockDTO;
	}

	public static MockDetailsDTO convertEntityToDto(Mock mock,boolean allDetails) {
		MockDetailsDTO mockDetailsDTO = new MockDetailsDTO();
		BeanUtils.copyProperties(mock, mockDetailsDTO);
		return mockDetailsDTO;
	}
}
