package com.te.lms.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "scheduled_mocks")
public class ScheduledMock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mockId;
	@ManyToOne(cascade = CascadeType.ALL)
	private Batch batch;
	private LocalDate date;
	private LocalTime time;
	
}
