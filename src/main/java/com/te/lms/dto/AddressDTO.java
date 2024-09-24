package com.te.lms.dto;

import com.te.lms.enums.AddressType;

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
public class AddressDTO {

	private String street;
	private Long pincode;
	private String doorNo;
	private String locality;
	private String landmark;
	
	private AddressType addressType;
	private String state;
	private String city;
}
