package com.lzx.repository;

import com.lzx.entity.QuartzLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartzLogRepository extends JpaRepository<QuartzLog,Long> {
}
