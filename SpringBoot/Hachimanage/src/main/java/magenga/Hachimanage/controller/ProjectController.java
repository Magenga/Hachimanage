package magenga.Hachimanage.controller;

import jakarta.websocket.OnClose;
import magenga.Hachimanage.common.dto.InviteUserRequest;
import magenga.Hachimanage.common.entity.Project;
import magenga.Hachimanage.common.entity.User;
import magenga.Hachimanage.common.exceprion.NotFoundException;
import magenga.Hachimanage.common.service.ProjectService;
import magenga.Hachimanage.common.service.ProjectUserRoleService;
import magenga.Hachimanage.common.service.UserQueryService;
import magenga.Hachimanage.systemMethod.SystemLogging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> createProject(@RequestHeader("account") String account, @RequestBody Project theProject) {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String userAccount;
//        if (principal instanceof UserDetails) {
//            userAccount = ((UserDetails) principal).getUsername();
//        } else {
//            userAccount = principal.toString();
//        }

        User user = userQueryService.findSeqByAccount(account);
        if (user == null) {
            throw new NotFoundException("User not found for account: " + account);
        }

        int userId = userQueryService.findSeqByAccount(account).getId();

        projectService.save(theProject);
        controllerSystemLogging.projectCreate(theProject);
        projectUserRoleService.projectCreating(theProject.getId(), userId);//設定創建人角色

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
    public ResponseEntity<Map<String, Object>> updateProject(@RequestBody Project theProject) {

        projectService.update(theProject);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "project updated");
        response.put("data", theProject);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/setProject/{user_id}")
    public ResponseEntity<Map<String, Object>> setUserProject(@PathVariable int user_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Project> allProject = projectUserRoleService.readProjectByUserId(user_id);
            if (allProject == null) {
                response.put("status", "no data");
                response.put("message", "找不到用戶的項目");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            response.put("status", "success");
            response.put("message", "找到用戶的所有項目");
            response.put("data", allProject);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "獲取項目時發生錯誤");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/invite")
    public ResponseEntity<Map<String, Object>> inviteUserToProject (@RequestBody InviteUserRequest request) {
        Map<String, Object> response = new HashMap<>();
        String requestAccount = request.getUserAccount();
        int requestProjectId = request.getProjectId();
        try {
            int requestUserId = userQueryService.findSeqByAccount(requestAccount).getId();
            projectUserRoleService.addUser(requestProjectId, requestUserId);

            response.put("status", "success");
            response.put("message", "邀請成功");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "找不到用戶或邀請失敗");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
