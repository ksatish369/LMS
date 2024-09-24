package com.te.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.EmployeeRequest;
import com.te.lms.entity.Mentor;

public interface EmployeeRequestRepository extends JpaRepository<EmployeeRequest,Integer>{

	List<EmployeeRequest> findByActionTaken(boolean b);

	Optional<EmployeeRequest> findByEmployeeIdAndRequestId(String employeeId, Integer requestNumber);

}
