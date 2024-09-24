package com.te.lms.service;

import java.util.List;

import com.te.lms.dto.AttendanceDTO;
import com.te.lms.dto.BatchAttendanceDTO;
import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.EmployeeDTO;
import com.te.lms.dto.EmployeeStatusDTO;
import com.te.lms.dto.MentorDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.dto.MockRatingDTO;
import com.te.lms.dto.PasswordDTO;

public interface MentorService {

	public String addMentor(MentorDTO mentorDTO);

	public String deleteMentor(String mentorId);

	public String updateMentor(MentorDTO mentorDTO);

	public String addMock(MockDTO mockDTO);

	public String giveMockRating(MockRatingDTO mockDTO);

	public List<EmployeeDTO> getEmployees(String batchName);

	public String submitAttendance(BatchAttendanceDTO batchAttendanceDTO);

	public List<AttendanceDTO> createAttendance(String batchId);

	public List<BatchDTO> getBatches(String mentorId);

	public String changeEmployeeStatus(EmployeeStatusDTO employeeStatusDTO);

	public String changePassword(PasswordDTO passwordDTO);

}
