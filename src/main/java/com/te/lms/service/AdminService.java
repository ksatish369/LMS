package com.te.lms.service;

import java.util.List;

import com.te.lms.dto.ApproveDTO;
import com.te.lms.dto.BatchDTO;
import com.te.lms.dto.RequestDTO;

public interface AdminService {

	public String addBatch(BatchDTO batchDTO);

	public List<String> getTechnologies();

	public List<String> getMentorNames();

	public List<RequestDTO> getRequests();

	public List<String> getBatchIds();

	public List<String> getBatchNames();

	public String approveEmployee(ApproveDTO approveDTO);

	public String updateBatch(BatchDTO batchDTO);

	public String deleteBatch(String batchId);

	public String rejectEmployee(ApproveDTO approveDTO);

}
