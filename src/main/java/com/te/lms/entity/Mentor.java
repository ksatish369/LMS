package com.te.lms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "mentor_details")
public class Mentor {

	@Id
	private String mentorId;
	private String mentorName;
	private String emailId;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "mentors")
	private List<Technology> technologies = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "mentors")
	private List<Batch> batches = new ArrayList<Batch>();
}
