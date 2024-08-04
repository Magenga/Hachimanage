package magenga.Hachimanage.common.service;

import magenga.Hachimanage.common.dao.ProjectRepository;
import magenga.Hachimanage.common.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void save (Project theProject) {
        projectRepository.save(theProject);
    }

    public void delete (Project theProject) {
        projectRepository.delete(theProject);
    }

    public void update (Project theProject) {
        projectRepository.save(theProject);
    }

    public List<Project> findAll () {
        return projectRepository.findAll();
    }

    public Project findById (int id) {
        Optional<Project> result = projectRepository.findById(id);
        Project theProject = null;
        if(result.isPresent()) {
            theProject = result.get();
        }
        return theProject;
    }


}
