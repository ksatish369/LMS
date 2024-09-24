package com.te.lms.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.te.lms.dto.ApproveDTO;
import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.RequestDTO;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Batch;
import com.te.lms.entity.EmailDetails;
import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeRequest;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Role;
import com.te.lms.enums.BatchStatus;
import com.te.lms.enums.BatchTechnology;
import com.te.lms.exception.BatchIdNotFoundException;
import com.te.lms.exception.MentorNotFoundException;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.EmployeeRequestRepository;
import com.te.lms.repository.MentorRepository;
import com.te.lms.security.repository.AppUserRepository;
import com.te.lms.security.repository.RoleRepository;
import com.te.lms.utils.AdminUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final BatchRepository batchRepository;
	private final MentorRepository mentorRepository;
	private final EmployeeRequestRepository employeeRequestRepository;
	private final EmployeeRepository employeeRepository;
	private final EmailService emailService;
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder; 

	@Override
	public String addBatch(BatchDTO batchDTO) {
		Batch batch = AdminUtils.convertDtoToEntity(batchDTO);
		batch.setBatchId(UUID.randomUUID().toString());
		Mentor mentor = mentorRepository.findByMentorName(batchDTO.getMentorName()).orElseThrow(
				() -> new MentorNotFoundException("Mentor with Name : " + batchDTO.getMentorName() + " not found!!"));
		batch.getMentors().add(mentor);
		batch.setBatchStatus(BatchStatus.TO_BE_STARTED);
		mentor.getBatches().add(batch);
		batchRepository.save(batch);
		return batch.getBatchId();
	}

	@Override
	public String updateBatch(BatchDTO batchDTO) {
		Batch batch = AdminUtils.convertDtoToEntity(batchDTO);
//		For updating,setting Id will do that
		batch.setBatchId(batchDTO.getBatchId());
		Optional<Batch> batchFromDB = batchRepository.findByMentors_MentorName(batchDTO.getMentorName());
//		If Mentor is updated,it will be added to batch and vice-versa
		if (batchFromDB.isEmpty()) {
			Mentor mentor = mentorRepository.findByMentorName(batchDTO.getMentorName())
					.orElseThrow(() -> new MentorNotFoundException(
							"Mentor with name " + batchDTO.getMentorName() + " is not found!!"));
			batch.getMentors().add(mentor);
			mentor.getBatches().add(batch);
		}
		batchRepository.save(batch);
		return batch.getBatchId();
	}

	@Override
	public List<RequestDTO> getRequests() {
		List<EmployeeRequest> employeeRequests = employeeRequestRepository.findByActionTaken(false);
		return employeeRequests.stream()
				.map(e -> RequestDTO.builder().contactNumber(e.getContactNumber()).employeeId(e.getEmployeeId())
						.employeeName(e.getEmployeeName()).percentage(e.getPercentage())
						.yearOfPassout(e.getYearOfPassout()).requestNumber(e.getRequestId())
						.YearsOfExperience(e.getYearsOfExperience()).build())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getTechnologies() {
		return Arrays.stream(BatchTechnology.values()).map(t -> t.getTechnology()).collect(Collectors.toList());
	}

	@Override
	public List<String> getMentorNames() {
		return mentorRepository.findAll().stream().map(m -> m.getMentorName()).collect(Collectors.toList());
	}

	@Override
	public List<String> getBatchIds() {
		return batchRepository.findAll().stream().map(b -> b.getBatchId()).collect(Collectors.toList());
	}

	@Override
	public List<String> getBatchNames() {
		return batchRepository.findAll().stream().map(b -> b.getBatchName()).collect(Collectors.toList());
	}

	@Override
	public String approveEmployee(ApproveDTO approveDTO) {
		Employee employee = employeeRepository.findById(approveDTO.getEmployeeId()).get();
		EmployeeRequest employeeRequest = employeeRequestRepository
				.findByEmployeeIdAndRequestId(approveDTO.getEmployeeId(), approveDTO.getRequestNumber()).get();
		employeeRequest.setActionTaken(true);
		employeeRequest.setApproved(true);
		Batch batch = batchRepository.findById(approveDTO.getBatchId())
				.orElseThrow(() -> new BatchIdNotFoundException("Batch Id not found!!"));
		batch.getEmployees().add(employee);
		employee.setBatch(batch);
		employeeRequestRepository.save(employeeRequest);
		batchRepository.save(batch);
		new Thread(()->emailService.sendMail(EmailDetails.builder().reciever(employee.getEmailId()).sender("satishcerous369@gmail.com")
				.subject("Registration Status")
				.messageBody("Hello " + employee.getEmployeeName()
						+ ",\n\nYour request for getting registered as employee in TestYantra has been approved!!"
						+ "\n Here is your login credentials : " + "\n Password : emp@123"
						+ "\n Note: Please rest your password after login is successful")
				.build())).start();
		AppUser appUser = AppUser.builder().employeeId(employee.getEmployeeId()).password(passwordEncoder.encode("emp@123")).build();
		Role role = roleRepository.findByRoleName("ROLE_EMPLOYEE").get();
		appUser.setRoles(List.of(role));
		role.getAppUsers().add(appUser);
		appUserRepository.save(appUser);
		return batch.getBatchId();
	}

	@Override
	public String rejectEmployee(ApproveDTO approveDTO) {
		Optional<EmployeeRequest> employeeRequest = employeeRequestRepository
				.findByEmployeeIdAndRequestId(approveDTO.getEmployeeId(), approveDTO.getRequestNumber());
		Employee employee = employeeRepository.findById(approveDTO.getEmployeeId()).get();
		employeeRequest.get().setActionTaken(true);
		employeeRequest.get().setReasonForRejection(approveDTO.getReasonForRejection());
		employeeRequestRepository.save(employeeRequest.get());
		new Thread(()->emailService.sendMail(EmailDetails.builder().reciever(employee.getEmailId()).sender("satishcerous369@gmail.com")
				.subject("Registration Status")
				.messageBody("Hello " + employee.getEmployeeName()
						+ ".\n Your request for getting registered as employee in TestYantra has been rejected!!"
						+ "\n Reason : "+approveDTO.getReasonForRejection())
				.build())).start();
		return approveDTO.getEmployeeId();
	}

	@Override
	public String deleteBatch(String batchId) {
		Batch batch = batchRepository.findById(batchId)
				.orElseThrow(() -> new BatchIdNotFoundException("Batch Id is not available!!"));
		batch.getEmployees().forEach(e -> e.setBatch(null));
		batch.getMentors().forEach(m -> {
			Iterator<Batch> iterator = m.getBatches().iterator();
			while (iterator.hasNext()) {
				Batch mentorBatch = iterator.next();
				if (mentorBatch.getBatchId().equals(batch.getBatchId()))
					iterator.remove();
			}
		});
		batch.getScheduledMocks().forEach(s -> s.setBatch(null));
		batch.setScheduledMocks(null);
		batch.setEmployees(null);
		batch.setMentors(null);
		batchRepository.save(batch);
		batchRepository.delete(batch);
		return batchId;
	}

}
