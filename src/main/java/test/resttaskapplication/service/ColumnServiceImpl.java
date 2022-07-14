package test.resttaskapplication.service;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import test.resttaskapplication.model.Column;
import test.resttaskapplication.repository.ColumnRepository;

@Service
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @Override
    public Column save(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public void delete(Long id) {
        columnRepository.deleteById(id);
    }

    @Override
    public List<Column> getAll(PageRequest pageRequest) {
        return columnRepository.findAll(pageRequest).toList();
    }
}
