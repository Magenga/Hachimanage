package magenga.TimeLedger.common.dao;

import magenga.TimeLedger.common.entity.Project;
import magenga.TimeLedger.common.entity.ProjectUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserRoleRepository extends JpaRepository<ProjectUserRole, Integer> {
}
