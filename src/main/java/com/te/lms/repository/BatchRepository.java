package com.te.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, String>{

	Optional<Batch> findByMentors_MentorName(String mentorName);

	Optional<Batch> findByBatchName(String batchName);

	List<Batch> findByMentors_MentorId(String mentorId);

}
