package com.lzx.service.impl;

import com.lzx.entity.QuartzJob;
import com.lzx.exception.BadRequestException;
import com.lzx.quartz.QuartzManage;
import com.lzx.repository.QuartzJobRepository;
import com.lzx.service.QuartzJobService;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private QuartzJobRepository quartzJobRepository;
    @Autowired
    private QuartzManage quartzManage;

    @Override
    public void updateIsPause(QuartzJob quartzJob) {
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        quartzJobRepository.save(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(QuartzJob quartzJob) {
        try {
            if (!CronExpression.isValidExpression(quartzJob.getCronExpression())){
                throw new BadRequestException("cron表达式格式错误");
            }
            quartzJobRepository.save(quartzJob);
            quartzManage.addJob(quartzJob);
        } catch (BadRequestException e) {

        }
    }
}
