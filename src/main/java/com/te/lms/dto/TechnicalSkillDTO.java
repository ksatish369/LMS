package com.te.lms.dto;

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
public class TechnicalSkillDTO {

	private String skilltype;
	private Integer yearOfExperience;
	private Integer skillRating;
}
