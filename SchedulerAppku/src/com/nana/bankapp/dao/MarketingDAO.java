package com.nana.bankapp.dao;

import java.util.List;
import java.util.UUID;

import com.nana.bankapp.model.Marketing;

public interface MarketingDAO {

	public boolean saveMarketing(Marketing marketing);
	public boolean editMarketing(Marketing marketing);
	public List<Marketing> getMarketings();
	public Marketing getMarketing(UUID marketingId);
	public boolean deleteMarketing(UUID marketingId);

}