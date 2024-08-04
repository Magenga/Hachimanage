package magenga.Hachimanage.common.service;

import magenga.Hachimanage.common.dao.ProjectRepository;
import magenga.Hachimanage.common.dao.ProjectUserRoleRepository;
import magenga.Hachimanage.common.entity.Project;
import magenga.Hachimanage.common.entity.ProjectUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectUserRoleService {

    @Autowired
    private ProjectUserRoleRepository projectUserRoleRepository;
    @Autowired
    private ProjectRepository projectRepository;

    //創建project的時候設定創建的人為最高權限
    public  void projectCreating (int projectId, int userId) {
        ProjectUserRole theCreator = new ProjectUserRole(projectId, userId, 1);
        projectUserRoleRepository.save(theCreator);
    }

    public List<Project> readProjectByUserId(int userId) {
        List<ProjectUserRole> allProjectByUser = projectUserRoleRepository.findByUserId(userId);
        List<Project> list = new ArrayList<>();
        for (ProjectUserRole projectUserRole : allProjectByUser) {
            Project project = projectRepository.findById(projectUserRole.getProjectId()).orElse(null);
            if (project != null) {
                list.add(project);
            }
        }
        return list;
    }

    public void addUser (int projectId, int userId) {
        ProjectUserRole addingUser = new ProjectUserRole(projectId, userId, 4);
        projectUserRoleRepository.save(addingUser);

    }

    public void setRole (ProjectUserRole settingUser,int level) {
        settingUser.setRoleId(level);
        projectUserRoleRepository.save(settingUser);
    }
}
