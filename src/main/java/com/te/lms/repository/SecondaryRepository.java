package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.EmployeeSecondaryInfo;

public interface SecondaryRepository extends JpaRepository<EmployeeSecondaryInfo, Integer> {

}
