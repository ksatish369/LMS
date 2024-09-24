package com.te.lms.utils;

import org.springframework.beans.BeanUtils;

import com.te.lms.dto.MentorDTO;
import com.te.lms.dto.MockDTO;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Mock;

public class MentorUtils {

	public static Mentor convertDtoToEntity(MentorDTO mentorDTO) {
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(mentorDTO, mentor);
		return mentor;
	}
	
	public static Mock convertDtoToEntity(MockDTO mockDTO) {
		Mock mock = new Mock();
		BeanUtils.copyProperties(mockDTO, mock);
		return mock;
	}
}
