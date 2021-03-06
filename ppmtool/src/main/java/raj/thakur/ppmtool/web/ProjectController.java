package raj.thakur.ppmtool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import raj.thakur.ppmtool.domain.Project;
import raj.thakur.ppmtool.services.MapValidationErrorService;
import raj.thakur.ppmtool.services.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		//Handling in case BindingResult object has errors
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
			
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}

	@GetMapping("/{projectIdentifier}")
	public ResponseEntity<?> getProjectByIdentifier(@PathVariable String projectIdentifier){
		
		Project project = projectService.findProjectByIdentifier(projectIdentifier);
		
		return new ResponseEntity<Project>(project,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects(){
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("/{projectIdentifier}")
	public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier){
		projectService.deleteProjectByIdentifier(projectIdentifier);
		return new ResponseEntity<String>("Project with ID:'"+projectIdentifier+"' was deleted successfully.", HttpStatus.OK);
	}
}
