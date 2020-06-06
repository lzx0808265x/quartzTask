package com.lzx.service;

import com.lzx.entity.QuartzJob;

public interface QuartzJobService {
    void updateIsPause(QuartzJob quartzJob);

    void create(QuartzJob quartzJob);
}
