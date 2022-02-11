package com.aus.repository;

import com.aus.model.Compound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompoundRepository extends JpaRepository<Compound, Integer> {
//    List<Compound> findByUsersId(long users_id);
    List<Compound> findByCompoundActive  (String compound_active);
}
