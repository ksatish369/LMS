package com.te.lms.service;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.lms.dto.AttendanceDTO;
import com.te.lms.dto.BatchAttendanceDTO;
import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.EmployeeStatusDTO;
import com.te.lms.dto.MentorDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.dto.MockRatingDTO;
import com.te.lms.dto.PasswordDTO;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Attendance;
import com.te.lms.entity.Batch;
import com.te.lms.entity.EmailDetails;
import com.te.lms.entity.Employee;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Mock;
import com.te.lms.entity.Role;
import com.te.lms.entity.ScheduledMock;
import com.te.lms.entity.Technology;
import com.te.lms.exception.BatchIdNotFoundException;
import com.te.lms.exception.MentorIdAlreadyPresentException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.exception.MockNotFoundException;
import com.te.lms.exception.PasswordInCorrectException;
import com.te.lms.exception.PasswordMismatchException;
import com.te.lms.exception.UserIdNotAvailable;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.MentorRepository;
import com.te.lms.repository.MockRepository;
import com.te.lms.repository.TechnologyRepository;
import com.te.lms.security.repository.AppUserRepository;
import com.te.lms.security.repository.RoleRepository;
import com.te.lms.utils.AdminUtils;
import com.te.lms.utils.EmployeeUtils;
import com.te.lms.utils.MentorUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

	private final MentorRepository mentorRepository;
	private final BatchRepository batchRepository;
	private final EmployeeRepository employeeRepository;
	private final EmailService emailService;
	private final MockRepository mockRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AppUserRepository appUserRepository;
	private final TechnologyRepository technologyRepository;

	@Override
	public String addMentor(MentorDTO mentorDTO) {
		Optional<Mentor> mentorFromDB = mentorRepository.findById(mentorDTO.getMentorId());
		if (mentorFromDB.isPresent())
			throw new MentorIdAlreadyPresentException(
					"Mentor with id : " + mentorDTO.getMentorId() + " is already present");
		AppUser appUser = AppUser.builder().employeeId(mentorDTO.getMentorId())
				.password(passwordEncoder.encode(mentorDTO.getEmailId())).build();
		Role role = roleRepository.findByRoleName("ROLE_MENTOR").get();
		new Thread(() -> emailService.sendMail(EmailDetails.builder().reciever(mentorDTO.getEmailId())
				.sender("satishcerous369@gmail.com").subject("Registration Status")
				.messageBody("Hello " + mentorDTO.getMentorName()
						+ ".\n\n You have been successfully added as mentor !!" + "\n Here is your login credentials : "
						+ "\n Password : your EmailId & employeeId is already provided"
						+ "\n Note: Please rest your password after login is successful")
				.build())).start();
		appUser.setRoles(List.of(role));
		role.getAppUsers().add(appUser);
		Mentor mentor = MentorUtils.convertDtoToEntity(mentorDTO);
		List<Technology> technologies = mentorDTO.getTechnologyNames().stream().map(t -> {
			Technology technology = technologyRepository.findByTechnology(t).get();
			technology.getMentors().add(mentor);
			return technology;
		}).toList();

		mentor.setTechnologies(technologies);
		mentorRepository.save(mentor);
		return mentorDTO.getMentorId();
	}

	@Override
	public String updateMentor(MentorDTO mentorDTO) {
		Mentor mentor = MentorUtils.convertDtoToEntity(mentorDTO);
		Mentor mentorFromDB = mentorRepository.findById(mentorDTO.getMentorId()).get();
		mentorDTO.getTechnologyNames().forEach(t -> {
			if(!mentorFromDB.getTechnologies().stream().anyMatch(t1->t1.getTechnology().matches(t))) {
				Technology technology = technologyRepository.findByTechnology(t).get();
				technology.getMentors().add(mentor);
				mentor.getTechnologies().add(technology);
			}});
		mentorRepository.save(mentor);
		return mentorDTO.getMentorId();
	}

	@Override
	public String deleteMentor(String mentorId) {
		Mentor mentor = mentorRepository.findById(mentorId)
				.orElseThrow(() -> new MentorNotFoundException("Mentor Id is not found!!"));
		mentor.getBatches().forEach(b -> {
			Iterator<Mentor> iterator = b.getMentors().iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getMentorId().equals(mentorId))
					iterator.remove();
			}
		});
		mentor.setBatches(null);
		AppUser mentorAppUser = appUserRepository.findByEmployeeId(mentorId).get();
		mentorAppUser.getRoles().forEach(r->{
		Iterator<AppUser> iterator2 = r.getAppUsers().iterator();
		while (iterator2.hasNext()) {
			if (iterator2.next().getEmployeeId().equals(mentorId))
				iterator2.remove();
		}
		});
		mentorAppUser.setRoles(null);
		appUserRepository.delete(mentorAppUser);
		mentor.getTechnologies().forEach(t -> {
			Iterator<Mentor> iterator = t.getMentors().iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getMentorId().equals(mentorId))
					iterator.remove();
			}
		});
		mentor.setTechnologies(null);
		mentorRepository.delete(mentor);
		return mentorId;
	}

	@Override
	public String addMock(MockDTO mockDTO) {
		Batch batch = batchRepository.findById(mockDTO.getBatchId())
				.orElseThrow(() -> new BatchIdNotFoundException("Batch Id : " + mockDTO.getBatchId() + " not found"));
		ScheduledMock scheduledMock = ScheduledMock.builder().batch(batch).date(mockDTO.getDate())
				.time(mockDTO.getTime()).build();
		batch.getScheduledMocks().add(scheduledMock);
		scheduledMock.setBatch(batch);
		batch.getEmployees().forEach(e -> {
			Mock mock = MentorUtils.convertDtoToEntity(mockDTO);
			e.getMocks().add(mock);
			mock.setEmployee(e);
			Technology technology = technologyRepository.findByTechnology(mockDTO.getTechnologyName()).get();
			mock.setTechnology(technology);
			technology.getMocks().add(mock);
			new Thread(() -> emailService.sendMail(EmailDetails.builder()
					.messageBody("Hello " + e.getEmployeeName() + " \n Your mock is scheduled on " + mockDTO.getDate()
							+ " Timings: " + mockDTO.getTime())
					.reciever(e.getEmailId()).sender("satishcerous@gmail.com").subject("Mock Allotment Details")
					.build())).start();
		});
		batchRepository.save(batch);
		return batch.getBatchId();
	}

	@Override
	public String giveMockRating(MockRatingDTO mockDTO) {
		Employee employee = employeeRepository.findById(mockDTO.getEmployeeId())
				.orElseThrow(() -> new UserIdNotAvailable("User Id not Found!!"));
//		If rating has to be given for one or more technologies from database
		List<Mock> mocks = mockRepository
				.findAllByTechnology(technologyRepository.findByTechnology(mockDTO.getTechnologyName()).get());
		if (mocks.isEmpty())
			throw new MockNotFoundException("Mock Details not found for provided Technology!!");
		Mock mock = mocks.get(mocks.size() - 1);
		mock.setMockRating(mockDTO.getOverallFeedback());
		mock.setDetailedFeedBack(mockDTO.getDetailedFeedBack());
		mock.setMockRating(mockDTO.getOverallFeedback());
		mock.setMockType(mockDTO.getMockType());
		mock.setPracticalKnowledge(mockDTO.getPracticalKnowledge());
		mock.setTheoreticalKnowledge(mockDTO.getTheoreticalKnowledge());
		employeeRepository.save(employee);
		return mockDTO.getEmployeeId();
	}

	@Override
	public List<EmployeeDTO> getEmployees(String batchName) {
		Batch batch = batchRepository.findByBatchName(batchName)
				.orElseThrow(() -> new BatchIdNotFoundException("Batch Name Not available!!"));
		return batch.getEmployees().stream().map(e -> EmployeeUtils.convertEntityToDto(e, true)).toList();
	}

	@Override
	public String submitAttendance(BatchAttendanceDTO batchAttendanceDTO) {
		Batch batch = batchRepository.findById(batchAttendanceDTO.getBatchId())
				.orElseThrow(() -> new BatchIdNotFoundException("Batch Id is Not Found!!"));
		batch.getEmployees().forEach(e -> {
			AttendanceDTO attendanceDTO = batchAttendanceDTO.getAttendanceDTOs().stream()
					.filter(a -> a.getEmployeeId().equals(e.getEmployeeId())).toList().get(0);
			e.getAttendances().add(Attendance.builder().afternoon(attendanceDTO.getAfternoon())
					.morning(attendanceDTO.getMorning()).date(attendanceDTO.getDate()).build());
		});
		batchRepository.save(batch);
		return "Updated Successfully!!";
	}

	@Override
	public List<AttendanceDTO> createAttendance(String batchId) {
		Batch batch = batchRepository.findById(batchId)
				.orElseThrow(() -> new BatchIdNotFoundException("Batch Id is Not Found!!"));
		return batch.getEmployees().stream().map(e -> AttendanceDTO.builder().date(LocalDate.now())
				.employeeId(e.getEmployeeId()).employeeName(e.getEmployeeName()).build()).toList();
	}

	@Override
	public List<BatchDTO> getBatches(String mentorId) {
		List<Batch> batches = batchRepository.findByMentors_MentorId(mentorId);
		return batches.stream().map(b -> {
			BatchDTO batchDTO = AdminUtils.convertEntityToDto(b);
			batchDTO.setMentorName(b.getMentors().get(0).getMentorName());
			return batchDTO;
		}).toList();
	}

	@Override
	public String changeEmployeeStatus(EmployeeStatusDTO employeeStatusDTO) {
		Employee employee = employeeRepository.findById(employeeStatusDTO.getEmployeeId())
				.orElseThrow(() -> new UserIdNotAvailable("User Id not available!!"));
		employee.setEmployeeStatus(employeeStatusDTO.getEmployeeStatus());
		employee.setReasonForLeaving(employeeStatusDTO.getReasonForLeaving());
		employeeRepository.save(employee);
		return employee.getEmployeeId();
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

}
