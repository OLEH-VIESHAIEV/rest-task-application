package test.resttaskapplication.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import test.resttaskapplication.model.Column;

public interface ColumnService {
    Column save(Column column);

    Column getById(Long id);

    void delete(Long id);

    List<Column> getAll(PageRequest pageRequest);
}
