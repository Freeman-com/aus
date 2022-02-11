package com.aus.repository;

import com.aus.model.LeftTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeftTableRepository extends JpaRepository<LeftTable, Integer> {
    List<LeftTable> findByUsersId(long userId);

}
