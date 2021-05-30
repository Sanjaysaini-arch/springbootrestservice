package com.hsbc.downstream.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hsbc.downstream.dto.DownStreamdto;


public interface DownStreamTransactionRecordRepository extends JpaRepository<DownStreamdto, Integer> {
}