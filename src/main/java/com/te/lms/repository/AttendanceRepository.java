package com.te.lms.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.te.lms.entity.Attendance;
import com.te.lms.entity.Employee;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	Attendance findByDate(LocalDate now);

	Optional<Attendance> findByDateAndEmployee(LocalDate now, Employee employee);

	@Query("select a from Attendance a group by Date ")
	List<Attendance> findTotalDays();

	Attendance findByEmployeeAndDate(Employee employee, LocalDate now);

}
