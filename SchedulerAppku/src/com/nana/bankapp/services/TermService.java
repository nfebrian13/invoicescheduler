package com.nana.bankapp.services;

import java.util.List;

import com.nana.bankapp.model.Term;

public interface TermService {

	public boolean saveTerm(Term term);
	public boolean editTerm(Term term);
	public List<Term> getTerm();
	public List<Term> pageTermList(Integer offset, Integer maxResults, boolean condition);
	public Term getTerm(String termId);
	public boolean deleteTerm(String termId);
	public Long count();
	
}