package com.te.lms.utils;

import org.springframework.beans.BeanUtils;

import com.te.lms.dto.BatchDTO;
import com.te.lms.entity.Batch;

public class AdminUtils {

	public static Batch convertDtoToEntity(BatchDTO batchDTO) {
		Batch batch = new Batch();
		BeanUtils.copyProperties(batchDTO, batch);
		return batch;
	}
	
	public static BatchDTO convertEntityToDto(Batch batch) {
		BatchDTO batchDTO = new BatchDTO();
		BeanUtils.copyProperties(batch, batchDTO);
		return batchDTO;
	}
}
