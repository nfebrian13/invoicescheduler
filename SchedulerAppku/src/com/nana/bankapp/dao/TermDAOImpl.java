package com.nana.bankapp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nana.bankapp.entity.TermEntity;
import com.nana.bankapp.model.Term;

@Repository
public class TermDAOImpl implements TermDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveTerm(Term term) {
		boolean saveFlag = true;
		TermEntity te = new TermEntity();
		te.setTermId(UUID.randomUUID().toString());
		te.setTermName(term.getTermName());
		te.setCreatedBy("User");
		te.setCreatedDate(new Date());
		te.setUpdatedBy(null);
		te.setUpdatedDate(null);
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.save(te);
		} catch (Exception e) {
			e.printStackTrace();
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public boolean editTerm(Term term) {
		boolean saveFlag = true;
		TermEntity te = new TermEntity();
		te.setTermId(term.getTermId());
		te.setTermName(term.getTermName());
		te.setCreatedBy(term.getCreatedBy());
		te.setCreatedDate(term.getCreatedDate());
		te.setUpdatedBy("User");
		te.setUpdatedDate(new Date());
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.update(te);
		} catch (Exception e) {
			e.printStackTrace();
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public List<Term> getTerm() {
		List<Term> list = new ArrayList<Term>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<TermEntity> query = session.createQuery("FROM TermEntity", TermEntity.class);
			List<TermEntity> terms = query.getResultList();

			for (int i = 0; i < terms.size(); i++) {
				TermEntity termEntity = (TermEntity) terms.get(i);
				Term te = new Term();
				te.setTermId(termEntity.getTermId());
				te.setTermName(termEntity.getTermName());
				te.setCreatedBy(termEntity.getCreatedBy());
				te.setCreatedDate(termEntity.getCreatedDate());
				te.setUpdatedBy(termEntity.getUpdatedBy());
				te.setUpdatedDate(termEntity.getUpdatedDate());
				list.add(te);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Term> pageTermList(Integer offset, Integer maxResults, boolean condition) {
		List<Term> list = new ArrayList<Term>();
		String querySql = "FROM TermEntity ORDER BY createdDate DESC";
		
		if (condition) {
			querySql = "FROM TermEntity ORDER BY updatedDate DESC";
		}
		
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<TermEntity> query = session.createQuery(querySql, TermEntity.class);
			query.setFirstResult(offset != null ? offset : 0);
			query.setMaxResults(maxResults != null ? maxResults : 10);

			List<TermEntity> rTerm = query.getResultList();

			if (rTerm != null) {
				for (int i = 0; i < rTerm.size(); i++) {
					TermEntity pe = (TermEntity) rTerm.get(i);
					Term pro = new Term();
					pro.setTermId(pe.getTermId());
					pro.setTermName(pe.getTermName());
					pro.setCreatedBy(pe.getCreatedBy());
					pro.setCreatedDate(pe.getCreatedDate());
					pro.setUpdatedBy(pe.getUpdatedBy());
					pro.setUpdatedDate(pe.getUpdatedDate());
					list.add(pro);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Term getTerm(String termId) {
		Term term = new Term();
		try {
			Session session = sessionFactory.getCurrentSession();
			TermEntity te = (TermEntity) session.load(TermEntity.class, termId);
			term.setTermId(te.getTermId());
			term.setTermName(te.getTermName());
			term.setCreatedBy(te.getCreatedBy());
			term.setCreatedDate(te.getCreatedDate());
			term.setUpdatedBy(te.getUpdatedBy());
			term.setUpdatedDate(te.getUpdatedDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return term;
	}

	@Override
	public boolean deleteTerm(String termId) {
		boolean deleteFlag = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			TermEntity ce = (TermEntity) session.load(TermEntity.class, termId);
			session.delete(ce);
		} catch (Exception e) {
			e.printStackTrace();
			deleteFlag = false;
		}
		return deleteFlag;
	}

	@Override
	public Long count() {
		Session session = sessionFactory.getCurrentSession();
		Long rowCount = null;
		try {
			Criteria criteria = session.createCriteria(TermEntity.class).setProjection(Projections.rowCount());
			List result = criteria.list();

			if (!result.isEmpty()) {
				rowCount = (Long) result.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

}
