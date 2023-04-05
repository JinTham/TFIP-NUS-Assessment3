package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private UserRepository userRepo;

    @Transactional(rollbackFor = InsertException.class)
    public void upsertTask(List<Task> taskList, String username) throws InsertException{
        Optional<User> opt = userRepo.findUserByUsername(username);
        String userId;
        if (opt.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setName(username.substring(0,1).toUpperCase() + username.substring(1));
            userId = userRepo.insertUser(user);
        } else {
            User user = opt.get();
            userId = user.getUserId();
        }
        
        for (Task task : taskList) {
            task.setUserId(userId);
            taskRepo.insertTask(task);
        }
    }
}
