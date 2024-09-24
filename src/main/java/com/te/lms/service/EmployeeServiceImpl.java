package com.te.lms.service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.te.lms.entity.Address;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.BankDetail;
import com.te.lms.entity.Contact;
import com.te.lms.entity.Education;
import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeRequest;
import com.te.lms.entity.EmployeeSecondaryInfo;
import com.te.lms.entity.Experience;
import com.te.lms.entity.TechnicalSkill;
import com.te.lms.enums.AccountType;
import com.te.lms.enums.AddressType;
import com.te.lms.enums.ContactType;
import com.te.lms.enums.Designation;
import com.te.lms.enums.EducationType;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.enums.Gender;
import com.te.lms.enums.MaritalStatus;
import com.te.lms.exception.EducationNotFoundException;
import com.te.lms.exception.PasswordInCorrectException;
import com.te.lms.exception.PasswordMismatchException;
import com.te.lms.exception.UserIdNotAvailable;
import com.te.lms.repository.AddressRepository;
import com.te.lms.repository.AttendanceRepository;
import com.te.lms.repository.ContactRepository;
import com.te.lms.repository.EducationRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.EmployeeRequestRepository;
import com.te.lms.repository.ExperienceRepository;
import com.te.lms.repository.SecondaryRepository;
import com.te.lms.repository.TechSkillRepository;
import com.te.lms.security.repository.AppUserRepository;
import com.te.lms.utils.EmployeeUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final SecondaryRepository secondaryRepository;
	private final AddressRepository addressRepository;
	private final TechSkillRepository techSkillRepository;
	private final EducationRepository educationRepository;
	private final ExperienceRepository experienceRepository;
	private final ContactRepository contactRepository;
	private final AttendanceRepository attendanceRepository;
	private final EmployeeRequestRepository employeeRequestRepository;
	private final PasswordEncoder passwordEncoder;
	private final AppUserRepository appUserRepository;
	private final ExcelService excelService;

	@Override
	public String savePrimaryInfo(PrimaryInfoDTO primaryInfoDTO) {
		Optional<Employee> employee = employeeRepository.findById(primaryInfoDTO.getEmployeeId());
		if (employee.isPresent())
			throw new UserIdNotAvailable("Id is already present");
		employeeRepository.save(EmployeeUtils.convertDtoToEntity(primaryInfoDTO));
		return primaryInfoDTO.getEmployeeId();
	}

	@Override
	public String updateSecondaryInfo(SecondaryInfoDTO secondaryInfoDTO, String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		EmployeeSecondaryInfo employeeSecondaryInfo = EmployeeUtils.convertDtoToEntity(secondaryInfoDTO);
//		If SecondaryInfo is already present, update it
		if (employee.getEmployeeSecondaryInfo() != null) {
			employeeSecondaryInfo
					.setEmployeeSecondaryInfoId(employee.getEmployeeSecondaryInfo().getEmployeeSecondaryInfoId());
		}
		employee.setEmployeeSecondaryInfo(employeeSecondaryInfo);
		employeeSecondaryInfo.setEmployee(employee);
		employeeRepository.save(employee);
		return employeeId;
	}

	@Override
	public String updateEductaionInfo(List<EducationDTO> educationDTOs, String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		if (employee.getEducationDetails().size() != 0) {
			employee.getEducationDetails().forEach(e -> {
				employee.setEducationDetails(null);
				educationRepository.delete(e);
			});
		}
		employee.setEducationDetails(educationDTOs.stream().map(e -> {
			Education education = EmployeeUtils.convertDtoToEntity(e);
			education.setEmployee(employee);
			return education;
		}).collect(Collectors.toList()));
		employeeRepository.save(employee);
		return employeeId;
	}

	@Override
	public String updateAddressInfo(List<AddressDTO> addressDTOs, String employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		if (employee.getAddressDetails().size() != 0)
			employee.getAddressDetails().forEach(a -> {
				employee.setAddressDetails(null);
				addressRepository.delete(a);
			});
		employee.setAddressDetails(addressDTOs.stream().map(e -> {
			Address address = EmployeeUtils.convertDtoToEntity(e);
			address.setEmployee(employee);
			return address;
		}).collect(Collectors.toList()));
		employeeRepository.save(employee);
		return employeeId;
	}

	@Override
	public String updateBankDetail(BankDTO bankDTO, String employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		BankDetail bankDetail = EmployeeUtils.convertDtoToEntity(bankDTO);
		if (employee.getBankDetail() != null)
			bankDetail.setBankId(employee.getBankDetail().getBankId());
		employee.setBankDetail(bankDetail);
		bankDetail.setEmployee(employee);
		employeeRepository.save(employee);
		return employeeId;
	}

	@Override
	public String updateTechSkills(List<TechnicalSkillDTO> technicalSkillDTOs, String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		if (employee.getTechnicalSkills().size() != 0)
			employee.getTechnicalSkills().forEach(t -> {
				employee.setTechnicalSkills(null);
				techSkillRepository.delete(t);
			});
		employee.setTechnicalSkills(technicalSkillDTOs.stream().map(e -> {
			TechnicalSkill technicalSkill = EmployeeUtils.convertDtoToEntity(e);
			technicalSkill.setEmployee(employee);
			return technicalSkill;
		}).collect(Collectors.toList()));
		employeeRepository.save(employee);
		return employeeId;
	}

	@Override
	public String updateExperience(List<ExperienceDTO> experienceDTOs, String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		if (employee.getExperiences().size() != 0)
			employee.getExperiences().stream().forEach(e -> {
				experienceRepository.delete(e);
				employee.setExperiences(null);
			});
		employee.setExperiences(experienceDTOs.stream().map(e -> {
			Experience experience = EmployeeUtils.convertDtoToEntity(e);
			experience.setEmployee(employee);
			return experience;
		}).collect(Collectors.toList()));
		employeeRepository.save(employee);
		return employeeId;
	}

	@Override
	public String updateContact(List<ContactDTO> contactDTOs, String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		if (employee.getContacts().size() != 0)
			employee.getContacts().forEach(c -> {
				contactRepository.delete(c);
			});
		employee.setContacts(contactDTOs.stream().map(e -> {
			Contact contact = EmployeeUtils.convertDtoToEntity(e);
			contact.setEmployee(employee);
			return contact;
		}).collect(Collectors.toList()));
		employeeRepository.save(employee);
		return employeeId;
	}

	@Override
	public PrimaryInfoDTO getPrimaryInfoDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return EmployeeUtils.convertEntityToDto(employee);
	}

	@Override
	public SecondaryInfoDTO getSecondaryInfoDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return EmployeeUtils.convertEntityToDto(employee.getEmployeeSecondaryInfo());
	}

	@Override
	public List<EducationDTO> getEducationDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return employee.getEducationDetails().stream().map(e -> EmployeeUtils.convertEntityToDto(e)).toList();
	}

	@Override
	public List<AddressDTO> getAddressDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return employee.getAddressDetails().stream().map(e -> EmployeeUtils.convertEntityToDto(e)).toList();
	}

	@Override
	public BankDTO getBankDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("User Id is not present"));
		if (employee.getBankDetail() != null)
			return EmployeeUtils.convertEntityToDto(employee.getBankDetail());
		else
			return null;

	}

	@Override
	public List<ExperienceDTO> getExperienceDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return employee.getExperiences().stream().map(e -> EmployeeUtils.convertEntityToDto(e)).toList();
	}

	@Override
	public List<TechnicalSkillDTO> getTechnicalSkillDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return employee.getTechnicalSkills().stream().map(e -> EmployeeUtils.convertEntityToDto(e)).toList();
	}

	@Override
	public List<AttendanceDTO> getAttendanceDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return employee.getAttendances().stream().map(e -> EmployeeUtils.convertEntityToDto(e)).toList();
	}

	@Override
	public List<MockDetailsDTO> getMockDTO(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("Id is not present in database"));
		return employee.getMocks().stream().map(e -> EmployeeUtils.convertEntityToDto(e, true)).toList();
	}

	@Override
	public Map<String, Integer> getAttendanceRatio(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("User Not Available!!"));
		int mrngCount = (int) employee.getAttendances().stream().filter(a -> a.isMorning()).count();
		int aftrCount = (int) employee.getAttendances().stream().filter(a -> a.isAfternoon()).count();
		int totalDays = attendanceRepository.findTotalDays().size();
		Map<String, Integer> attendance = new HashMap<String, Integer>();
		attendance.put("Morning", mrngCount);
		attendance.put("AfterNoon", aftrCount);
		attendance.put("TotalDays", totalDays);
		return attendance;
	}

	@Override
	public String submitRequest(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("User Id not found"));
		Education higherEducation = employee.getEducationDetails().stream()
				.max((o1, o2) -> o1.getYearOfPassing().compareTo(o2.getYearOfPassing()))
				.orElseThrow(() -> new EducationNotFoundException("Education value is null"));
		EmployeeRequest employeeRequest = EmployeeRequest.builder().employeeId(employeeId)
				.yearOfPassout(higherEducation.getYearOfPassing()).employeeName(employee.getEmployeeName())
				.percentage(higherEducation.getPercentage()).build();
		if (employee.getExperiences().size() != 0)
			employeeRequest.setYearsOfExperience(employee.getExperiences().stream().reduce(0D,
					(sum, a) -> sum + a.getYearOfExperience(), Double::sum));
		else
			employeeRequest.setYearsOfExperience(0D);
		if (employee.getContacts().size() != 0)
			employeeRequest.setContactNumber(employee.getContacts().get(0).getContactNumber());
		else
			employeeRequest.setContactNumber(0L);
		employeeRequestRepository.save(employeeRequest);
		return employeeId;
	}

//	@Override
//	public String submitRequest(String employeeId) {
//		Optional<Employee> employee = employeeRepository.findById(employeeId);
//		if (employee.isPresent()) {
//			Optional<EmployeeRequest> employeeRequestFromDB = employeeRequestRepository.findByEmployeeId(employeeId);
//			if (employeeRequestFromDB.isEmpty()) {
//				EmployeeRequest employeeRequest = EmployeeRequest.builder().employeeId(employeeId).build();
//				employeeRequestRepository.save(employeeRequest);
//				return employeeId;
//			}
//			throw new RequestAlreadySubmittedException("Employee with Id " + employeeId + " is already submitted!!");
//		}
//		throw new UserIdNotAvailable("User Id is not present");
//
//	}

	@Override
	public List<String> getDesignation() {
		return Arrays.stream(Designation.values()).map(d -> d.getDesignation()).collect(Collectors.toList());
	}

	@Override
	public List<String> getGender() {
		return Arrays.stream(Gender.values()).map(d -> d.getGender()).collect(Collectors.toList());

	}

	@Override
	public List<String> getEmployeeStatus() {
		return Arrays.stream(EmployeeStatus.values()).map(d -> d.getEmployeeStatus()).collect(Collectors.toList());

	}

	@Override
	public List<String> getMaritalStatus() {
		return Arrays.stream(MaritalStatus.values()).map(d -> d.getMaritalStatus()).collect(Collectors.toList());
	}

	@Override
	public List<String> getEducationType() {
		return Arrays.stream(EducationType.values()).map(d -> d.getEducationType()).collect(Collectors.toList());
	}

	@Override
	public List<String> getAddressType() {
		return Arrays.stream(AddressType.values()).map(d -> d.getAddressType()).collect(Collectors.toList());
	}

	@Override
	public List<String> getContactType() {
		return Arrays.stream(ContactType.values()).map(d -> d.getContactType()).collect(Collectors.toList());
	}

	@Override
	public List<String> getAccountType() {
		return Arrays.stream(AccountType.values()).map(d -> d.getAccountType()).collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployee(String employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new UserIdNotAvailable("User Id not found!!"));
		return EmployeeUtils.convertEntityToDto(employee, true);
	}

	@Override
	public String changePassword(PasswordDTO passwordDTO) {
		AppUser mentor = appUserRepository.findByEmployeeId(passwordDTO.getEmployeeId()).get();
		if (!passwordEncoder.matches(passwordDTO.getOldPassword(), mentor.getPassword()))
			throw new PasswordInCorrectException("Please enter the correct password!!");
		if (!passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword()))
			throw new PasswordMismatchException("Please enter the New password as same as Confirm password!!");
		mentor.setPassword(passwordEncoder.encode(passwordDTO.getConfirmPassword()));
		appUserRepository.save(mentor);
		return passwordDTO.getEmployeeId();
	}
	
	@Override
	public boolean addDataFromExcelToDatabase(InputStream inputStream) {
		List<Employee> employees = excelService.convertExcelToList(inputStream);
		employees.stream().forEach(employeeRepository::save);
		return true;
	}

}
