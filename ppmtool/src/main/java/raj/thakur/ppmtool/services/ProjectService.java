package raj.thakur.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import raj.thakur.ppmtool.domain.Project;
import raj.thakur.ppmtool.exceptions.ProjectIdException;
import raj.thakur.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project saveOrUpdateProject(Project project) {
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIdException("Project ID'" + project.getProjectIdentifier().toUpperCase()
					+ "' already exists. It should be unique.");
		}
	}
	
}
