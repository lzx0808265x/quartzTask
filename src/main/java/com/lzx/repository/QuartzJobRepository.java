package com.lzx.repository;

import com.lzx.entity.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuartzJobRepository extends JpaRepository<QuartzJob,Long> {
    List<QuartzJob> findByIsPauseIsFalse();
}
