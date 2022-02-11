package com.aus.repository;

import com.aus.model.exchangeEntity.BitMaxAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitMaxRepository extends JpaRepository<BitMaxAccount, Integer> {

    BitMaxAccount findByEmail (String bitmaxemail);
    List<BitMaxAccount> findByUsersId (long userId);

}
