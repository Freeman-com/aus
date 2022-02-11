package com.aus.repository;

import com.aus.model.XUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJPA extends JpaRepository<XUser, Integer> {

    XUser findByEmail (String email);


}
