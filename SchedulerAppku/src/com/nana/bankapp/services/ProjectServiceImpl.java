package com.nana.bankapp.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nana.bankapp.dao.ProjectDAO;
import com.nana.bankapp.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDAO projectDAO;

	@Override
	@Transactional
	public boolean saveProject(Project project) {
		return projectDAO.saveProject(project);
	}

	@Override
	@Transactional
	public boolean editProject(Project project) {
		return projectDAO.editProject(project);
	}

	@Override
	@Transactional
	public List<Project> getProjects() {
		return projectDAO.getProjects();
	}

	@Override
	@Transactional
	public Project getProject(UUID projectId) {
		return projectDAO.getProject(projectId);
	}

	@Override
	@Transactional
	public boolean deleteProject(UUID projectId) {
		return projectDAO.deleteProject(projectId);
	}

}