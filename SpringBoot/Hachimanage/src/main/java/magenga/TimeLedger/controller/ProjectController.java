package magenga.TimeLedger.controller;

import magenga.TimeLedger.common.entity.Project;
import magenga.TimeLedger.common.service.ProjectService;
import magenga.TimeLedger.common.service.ProjectUserRoleService;
import magenga.TimeLedger.common.service.UserQueryService;
import magenga.TimeLedger.systemMethod.SystemLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private SystemLogging controllerSystemLogging;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserQueryService userQueryService;
    @Autowired
    private ProjectUserRoleService projectUserRoleService;

    @GetMapping("/project")
    public ResponseEntity<Map<String, Object>> readAllProject() {
        List<Project> projects = projectService.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", projects);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/project/{project_id}")
    public ResponseEntity<Map<String, Object>> readOneProject(@PathVariable int project_id) {
        Project project = projectService.findById(project_id);
        if (project == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Project not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", project);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/project")
    public ResponseEntity<Map<String, Object>> createProject(@RequestBody Project theProject) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userAccount;
        if (principal instanceof UserDetails) {
            userAccount = ((UserDetails) principal).getUsername();
        } else {
            userAccount = principal.toString();
        }
        int userId = userQueryService.findSeqByAccount(userAccount).getId();

        projectService.save(theProject);
        controllerSystemLogging.projectCreate(theProject);
        projectUserRoleService.projectCreating(theProject.getId(),userId);//設定創建人角色

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Project created successfully");
        response.put("data", theProject);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/project/{project_id}")
    public ResponseEntity<Map<String, Object>> deleteProject(@PathVariable int project_id) {
        Project project = projectService.findById(project_id);
        if (project == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Project not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        projectService.delete(project);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Project deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/project")
    public ResponseEntity<Map<String, Object>> updateProject (@RequestBody Project theProject) {

        projectService.update(theProject);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "project updated");
        response.put("data", theProject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
