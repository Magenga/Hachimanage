package magenga.TimeLedger.common.service;

import magenga.TimeLedger.common.dao.ProjectUserRoleRepository;
import magenga.TimeLedger.common.entity.ProjectUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectUserRoleService {

    @Autowired
    private ProjectUserRoleRepository projectUserRoleRepository;

    //創建project的時候設定創建的人為最高權限
    public  void projectCreating (int projectId, int userId) {
        ProjectUserRole theCreator = new ProjectUserRole(projectId, userId, 1);
        projectUserRoleRepository.save(theCreator);
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
