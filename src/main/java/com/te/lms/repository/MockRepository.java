package com.te.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Mock;
import com.te.lms.entity.Technology;

public interface MockRepository extends JpaRepository<Mock,Integer>{

	public List<Mock> findAllByTechnology(Technology technology);

}
