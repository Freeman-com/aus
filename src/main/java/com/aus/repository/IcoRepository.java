package com.aus.repository;

import com.aus.model.ICO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcoRepository extends JpaRepository<ICO, Integer> {

    ICO findByName (String name);

}
