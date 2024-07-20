package magenga.TimeLedger.common.dao;

import magenga.TimeLedger.common.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {


}
