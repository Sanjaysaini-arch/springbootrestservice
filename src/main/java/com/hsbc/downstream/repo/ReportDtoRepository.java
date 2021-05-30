package com.hsbc.downstream.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hsbc.downstream.dto.ReportDto;





public interface ReportDtoRepository extends JpaRepository<ReportDto, Integer> {
}