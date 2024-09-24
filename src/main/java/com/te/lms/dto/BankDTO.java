package com.te.lms.dto;

import com.te.lms.enums.AccountType;

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
public class BankDTO {

	private String accountNumber;
	private String bankName;
	private String ifscCode;
	private String branch;
	private String state;
	
	private AccountType accountType;
}
