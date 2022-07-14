package test.resttaskapplication.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.resttaskapplication.dto.request.TaskBoardRequestDto;
import test.resttaskapplication.dto.response.TaskBoardResponseDto;
import test.resttaskapplication.mapper.TaskBoardDtoMapper;
import test.resttaskapplication.service.TaskBoardService;

@RestController
@RequestMapping("/task_boards")
public class TaskBoardController {
    private final TaskBoardService taskBoardService;
    private final TaskBoardDtoMapper taskBoardDtoMapper;

    public TaskBoardController(TaskBoardService taskBoardService,
                               TaskBoardDtoMapper taskBoardDtoMapper) {
        this.taskBoardService = taskBoardService;
        this.taskBoardDtoMapper = taskBoardDtoMapper;
    }

    @PostMapping
    public TaskBoardResponseDto save(@RequestBody TaskBoardRequestDto requestDto) {
        return taskBoardDtoMapper.mapToDto(taskBoardService
                .save(taskBoardDtoMapper.mapToModel(requestDto)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskBoardService.delete(id);
    }

    @GetMapping
    public List<TaskBoardResponseDto> getAll() {
        return taskBoardService
                .getAll()
                .stream()
                .map(taskBoardDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
