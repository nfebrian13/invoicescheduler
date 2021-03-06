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

import com.nana.bankapp.entity.ProjectEntity;
import com.nana.bankapp.model.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveProject(Project project) {
		boolean saveFlag = true;
		ProjectEntity pe = new ProjectEntity();
		pe.setProjectId(UUID.randomUUID().toString());
		pe.setProjectName(project.getProjectName());
		pe.setCreatedBy("User");
		pe.setCreatedDate(new Date());
		pe.setUpdatedBy(null);
		pe.setUpdatedDate(null);
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.save(pe);
		} catch (Exception e) {
			e.printStackTrace();
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public boolean editProject(Project project) {
		boolean saveFlag = true;
		ProjectEntity pe = new ProjectEntity();
		pe.setProjectId(project.getProjectId());
		pe.setProjectName(project.getProjectName());
		pe.setCreatedBy(project.getCreatedBy());
		pe.setCreatedDate(project.getCreatedDate());
		pe.setUpdatedBy("User");
		pe.setUpdatedDate(new Date());
		
		try {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.update(pe);
		} catch (Exception e) {
			e.printStackTrace();
			saveFlag = false;
		}
		return saveFlag;
	}

	@Override
	public List<Project> getProjects() {
		List<Project> list = new ArrayList<Project>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<ProjectEntity> query = session.createQuery("FROM ProjectEntity", ProjectEntity.class);
			List<ProjectEntity> projects = query.getResultList();

			for (int i = 0; i < projects.size(); i++) {
				ProjectEntity projectEntity = (ProjectEntity) projects.get(i);
				Project pe = new Project();
				pe.setProjectId(projectEntity.getProjectId());
				pe.setProjectName(projectEntity.getProjectName());
				pe.setCreatedBy(projectEntity.getCreatedBy());
				pe.setCreatedDate(projectEntity.getCreatedDate());
				pe.setUpdatedBy(projectEntity.getUpdatedBy());
				pe.setUpdatedDate(projectEntity.getUpdatedDate());
				list.add(pe);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Project> pageProjectList(Integer offset, Integer maxResults, boolean condition) {
		List<Project> list = new ArrayList<Project>();
		String querySql = "FROM ProjectEntity ORDER BY createdDate DESC";
		
		if (condition) {
			querySql = "FROM ProjectEntity ORDER BY updatedDate DESC";
		}
		try {
			Session session = sessionFactory.getCurrentSession();
			Query<ProjectEntity> query = session.createQuery(querySql, ProjectEntity.class);
			query.setFirstResult(offset != null ? offset : 0);
			query.setMaxResults(maxResults != null ? maxResults : 10);

			List<ProjectEntity> rProject = query.getResultList();

			if (rProject != null) {
				for (int i = 0; i < rProject.size(); i++) {
					ProjectEntity pe = (ProjectEntity) rProject.get(i);
					Project pro = new Project();
					pro.setProjectId(pe.getProjectId());
					pro.setProjectName(pe.getProjectName());
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
	public Project getProject(String projectId) {
		Project pr = new Project();
		try {
			Session session = sessionFactory.getCurrentSession();
			ProjectEntity pe = (ProjectEntity) session.load(ProjectEntity.class, projectId);
			pr.setProjectId(pe.getProjectId());
			pr.setProjectName(pe.getProjectName());
			pr.setCreatedBy(pe.getCreatedBy());
			pr.setCreatedDate(pe.getCreatedDate());
			pr.setUpdatedBy(pe.getUpdatedBy());
			pr.setUpdatedDate(pe.getUpdatedDate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pr;
	}

	@Override
	public boolean deleteProject(String projectId) {
		boolean deleteFlag = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			ProjectEntity ce = (ProjectEntity) session.load(ProjectEntity.class, projectId);
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
			Criteria criteria = session.createCriteria(ProjectEntity.class).setProjection(Projections.rowCount());
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
