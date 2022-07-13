package test.resttaskapplication.mapper;

import org.springframework.stereotype.Component;
import test.resttaskapplication.dto.request.TaskRequestDto;
import test.resttaskapplication.dto.response.TaskResponseDto;
import test.resttaskapplication.model.Task;
import test.resttaskapplication.repository.ColumnRepository;

@Component
public class TaskDtoMapper {
    private final ColumnRepository columnRepository;

    public TaskDtoMapper(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public Task mapToModel(TaskRequestDto requestDto) {
        Task task = new Task();
        task.setName(requestDto.getName());
        task.setDescription(requestDto.getDescription());
        task.setDateOfCreation(requestDto.getDateOfCreation());
        task.setColumn(columnRepository.getById(requestDto.getColumnId()));
        return task;
    }

    public TaskResponseDto mapToDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId(task.getId());
        taskResponseDto.setName(task.getName());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setDateOfCreation(task.getDateOfCreation());
        taskResponseDto.setColumnId(task.getColumn().getId());
        return taskResponseDto;
    }
}
