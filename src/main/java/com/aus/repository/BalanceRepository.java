package com.aus.repository;

import com.aus.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Double> {

   List <Balance> findByTotalBalance (double total_balance);
}
