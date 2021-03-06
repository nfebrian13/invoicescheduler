package com.nana.bankapp.services;

import java.util.List;

import com.nana.bankapp.model.Remarks;

public interface RemarksService {

	public boolean saveRemarks(Remarks remarks);
	public boolean editRemarks(Remarks remarks);
	public List<Remarks> getRemarks();
	public List<Remarks> pageRemarksList(Integer offset, Integer maxResults, boolean condition);
	public Remarks getRemarks(String remarksId);
	public boolean deleteRemarks(String remarksId);
	public Long count();
}