package com.te.lms.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.te.lms.enums.Designation;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.enums.Gender;

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
@Table(name = "employee_primary_info")
public class Employee {

	@Id
	private String employeeId;

	@NotBlank(message = "Name must not be null!!")
	private String employeeName;
	@Column(unique = true)
	@Email(message = "Email incorrect")
	private String emailId;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoin;
	private String bloodGroup;
	private String nationality;
	private Double salary;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private EmployeeStatus employeeStatus;

	@Enumerated(EnumType.STRING)
	private Designation designation;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private EmployeeSecondaryInfo employeeSecondaryInfo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Education> educationDetails;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Address> addressDetails;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BankDetail bankDetail;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<TechnicalSkill> technicalSkills = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Experience> experiences = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Contact> contacts = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Mock> mocks = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Attendance> attendances = new ArrayList<Attendance>();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Batch batch;

	private String reasonForLeaving;
	
	private boolean isDeleted;
}
