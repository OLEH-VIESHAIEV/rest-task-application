package test.resttaskapplication.service;

import java.util.List;
import org.springframework.stereotype.Service;
import test.resttaskapplication.model.Task;
import test.resttaskapplication.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task get(Long id) {
        return taskRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }
}
