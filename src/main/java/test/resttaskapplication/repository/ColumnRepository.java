package test.resttaskapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.resttaskapplication.model.Column;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Long> {
}
