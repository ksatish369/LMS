package com.te.lms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.te.lms.enums.BatchStatus;
import com.te.lms.enums.BatchTechnology;

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
@Table(name = "batch_details")
public class Batch {

	@Id
	private String batchId;
	private String batchName;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer batchNo;
	private LocalDate startDate;
	private LocalDate endDate;
		
	@Enumerated(EnumType.STRING)
	private BatchTechnology technology;
	
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;
	
	@JoinTable(name = "batch_mentor",joinColumns = @JoinColumn(name = "batch_id"),inverseJoinColumns = @JoinColumn(name = "mentor_id"))
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Mentor> mentors = new ArrayList<Mentor>();
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "batch")
	private List<Employee> employees = new ArrayList<Employee>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "batch")
	private List<ScheduledMock> scheduledMocks = new ArrayList<ScheduledMock>();
	
}
