package com.te.lms.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.te.lms.enums.MockNumber;
import com.te.lms.enums.MockRating;
import com.te.lms.enums.MockType;

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
@Table(name = "mock_details")
public class Mock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mockId;
	private LocalDate date;
	private LocalTime time;
	private String panel;
	private Double practicalKnowledge;
	private Double theoreticalKnowledge;
	private String detailedFeedBack;

	@Enumerated(EnumType.STRING)
	private MockNumber mockNumber;
		
	@Enumerated(EnumType.STRING)
	private MockType mockType;
	
	@Enumerated(EnumType.STRING)
	private MockRating mockRating;
	
	@ManyToOne
	private Technology technology;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Employee employee;

}
