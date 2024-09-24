package com.te.lms.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.te.lms.dto.AddressDTO;
import com.te.lms.dto.AttendanceDTO;
import com.te.lms.dto.BankDTO;
import com.te.lms.dto.ContactDTO;
import com.te.lms.dto.EducationDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.ExperienceDTO;
import com.te.lms.dto.MockDetailsDTO;
import com.te.lms.dto.PasswordDTO;
import com.te.lms.dto.PrimaryInfoDTO;
import com.te.lms.dto.SecondaryInfoDTO;
import com.te.lms.dto.TechnicalSkillDTO;

public interface EmployeeService {

	public String savePrimaryInfo(PrimaryInfoDTO primaryInfoDTO);

	public String updateSecondaryInfo(SecondaryInfoDTO secondaryInfoDTO, String employeeId);

	public String updateEductaionInfo(List<EducationDTO> educationDTOs, String employeeId);

	public String updateAddressInfo(List<AddressDTO> addressDTOs, String employeeId);

	public String updateBankDetail(BankDTO bankDTO, String employeeId);

	public String updateTechSkills(List<TechnicalSkillDTO> technicalSkillDTOs, String employeeId);

	public String updateExperience(List<ExperienceDTO> experienceDTOs, String employeeId);

	public String updateContact(List<ContactDTO> contactDTOs, String employeeId);

	public PrimaryInfoDTO getPrimaryInfoDTO(String employeeId);

	public SecondaryInfoDTO getSecondaryInfoDTO(String employeeId);

	public List<EducationDTO> getEducationDTO(String employeeId);

	public List<AddressDTO> getAddressDTO(String employeeId);

	public BankDTO getBankDTO(String employeeId);

	public List<ExperienceDTO> getExperienceDTO(String employeeId);

	public List<TechnicalSkillDTO> getTechnicalSkillDTO(String employeeId);

	public List<AttendanceDTO> getAttendanceDTO(String employeeId);

	public List<MockDetailsDTO> getMockDTO(String employeeId);

	public Map<String, Integer> getAttendanceRatio(String employeeId);

	public String submitRequest(String employeeId);

	public List<String> getDesignation();

	public List<String> getGender();

	public List<String> getEmployeeStatus();

	public List<String> getMaritalStatus();

	public List<String> getEducationType();

	public List<String> getAddressType();

	public List<String> getContactType();

	public List<String> getAccountType();

	public EmployeeDTO getEmployee(String employeeId);
	
	public String changePassword(PasswordDTO passwordDTO);

	public boolean addDataFromExcelToDatabase(InputStream inputStream);

}
