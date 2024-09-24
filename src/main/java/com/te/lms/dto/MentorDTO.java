package com.te.lms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MentorDTO {

	private String mentorId;
	private String mentorName;
	private String emailId;
	private List<String> technologyNames;
}
