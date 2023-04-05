package ibf2022.paf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERTTASK = "insert into task (description, priority, due_date) values (?,?,?)";

    public Boolean insertTask(Task task) {
        int inserted = jdbcTemplate.update(SQL_INSERTTASK,task.getDescription(),task.getPriority(),task.getDueDate());
        return inserted>0;
    }
}
