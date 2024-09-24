package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Education;

public interface EducationRepository extends JpaRepository<Education, Integer> {

}
