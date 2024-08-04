package magenga.Hachimanage.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Entity
//@Table()
public class Task {

    @Id
    private int id;
    private String taskName;
    private int projectId;
    private LocalDate deadline;
    private LocalDate startTime;
    private int memberId;
    private int commanderId;
    private int priority;
    private boolean done;
    private String status;
    private Float completion;
    private String describe;
}
