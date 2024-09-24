package com.te.lms.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer>{

	Optional<AppUser> findByEmployeeId(String employeeId);

}
