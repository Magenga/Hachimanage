package magenga.Hachimanage.common.dao;

import magenga.Hachimanage.common.entity.ProjectUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectUserRoleRepository extends JpaRepository<ProjectUserRole, Integer> {
    List<ProjectUserRole> findByUserId(int userId);

}
