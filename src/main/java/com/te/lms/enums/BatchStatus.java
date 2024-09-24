package com.te.lms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BatchStatus {

	TO_BE_STARTED("TO_BE_STARTED"), IN_PROGRESS("IN_PROGRESS"), COMPLETED("COMPLETED");

	private final String BatchStatus;
}
