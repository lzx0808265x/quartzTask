package com.lzx.quartz;

import com.lzx.entity.QuartzJob;
import com.lzx.entity.QuartzLog;
import com.lzx.holder.SpringContextHolder;
import com.lzx.repository.QuartzLogRepository;
import com.lzx.service.QuartzJobService;
import com.lzx.util.ThreadPoolExecutorUtil;
import com.lzx.util.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ExecutionJob extends QuartzJobBean {

    private final static ThreadPoolExecutor EXECUTOR = ThreadPoolExecutorUtil.getPoll();

//    @Autowired
//    private QuartzLogRepository quartzLogRepository;
//    @Autowired
//    private QuartzJobService quartzJobService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        QuartzJob quartzJob= (QuartzJob) jobExecutionContext.getMergedJobDataMap().get(QuartzJob.JOB_KEY);
        // 获取spring bean
        QuartzLogRepository quartzLogRepository = SpringContextHolder.getBean(QuartzLogRepository.class);
        QuartzJobService quartzJobService = SpringContextHolder.getBean(QuartzJobService.class);
        QuartzLog quartzLog = new QuartzLog();
        quartzLog.setJobName(quartzJob.getJobName());
        quartzLog.setBeanName(quartzJob.getBeanName());
        quartzLog.setMethodName(quartzJob.getMethodName());
        quartzLog.setParams(quartzJob.getParams());
        long startTime = System.currentTimeMillis();
        quartzLog.setCronExpression(quartzJob.getCronExpression());
        try {
            // 执行任务
            log.info("任务准备执行，任务名称：{}", quartzJob.getJobName());
            QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(),
                    quartzJob.getParams());
            Future<?> future = EXECUTOR.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            quartzLog.setTime(times);
            // 任务状态
            quartzLog.setIsSuccess(true);
            log.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒", quartzJob.getJobName(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务名称：{}" + quartzJob.getJobName(), e);
            long times = System.currentTimeMillis() - startTime;
            quartzLog.setTime(times);
            // 任务状态 0：成功 1：失败
            quartzLog.setIsSuccess(false);
            quartzLog.setExceptionDetail(ThrowableUtil.getStackTrace(e));
            quartzJob.setIsPause(false);
            //更新状态
            quartzJobService.updateIsPause(quartzJob);
        } finally {
            quartzLogRepository.save(quartzLog);
        }
    }
}
