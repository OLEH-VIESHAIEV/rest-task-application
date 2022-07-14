package test.resttaskapplication.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.resttaskapplication.dto.request.TaskRequestDto;
import test.resttaskapplication.dto.response.TaskResponseDto;
import test.resttaskapplication.mapper.TaskDtoMapper;
import test.resttaskapplication.model.Task;
import test.resttaskapplication.service.SortService;
import test.resttaskapplication.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final SortService sortService;
    private final TaskDtoMapper taskDtoMapper;

    public TaskController(TaskService taskService,
                          SortService sortService,
                          TaskDtoMapper taskDtoMapper) {
        this.taskService = taskService;
        this.sortService = sortService;
        this.taskDtoMapper = taskDtoMapper;
    }

    @PostMapping
    public TaskResponseDto save(@RequestBody TaskRequestDto requestDto) {
        return taskDtoMapper.mapToDto(taskService.save(taskDtoMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    public TaskResponseDto update(@PathVariable Long id,
                                  @RequestBody TaskRequestDto requestDto) {
        Task task = taskDtoMapper.mapToModel(requestDto);
        task.setId(id);
        return taskDtoMapper.mapToDto(taskService.save(task));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @GetMapping
    public List<TaskResponseDto> getAll(@RequestParam(defaultValue = "20")
                                        Integer count,
                                        @RequestParam(defaultValue = "0")
                                        Integer page,
                                        @RequestParam(defaultValue = "id")
                                        String sortBy) {
        Sort sort = Sort.by(sortService.parseSortedOrders(sortBy));
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        return taskService
                .getAll(pageRequest)
                .stream()
                .map(taskDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
