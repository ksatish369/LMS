package com.te.lms.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "technologies_info")
public class Technology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer technologyId;
	
	private String technology;
	
	@OneToMany(mappedBy = "technology")
	private List<Mock> mocks = new ArrayList<>();
	
	@JoinTable(name = "map_technology_mentor", joinColumns = @JoinColumn(name = "technologyId"),inverseJoinColumns = @JoinColumn(name = "mentorId"))
	@ManyToMany()
	private List<Mentor> mentors = new ArrayList<>();
}
