package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Integer>{

	Optional<Technology> findByTechnology(String string);

}
