package com.lzx;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.lzx.entity.Order;
import com.lzx.entity.QuartzJob;
import com.lzx.quartz.QuartzManage;
import com.lzx.service.QuartzJobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class TaskDemoApplicationTests {

    @Autowired
    private QuartzManage quartzManage;
    @Autowired
    private QuartzJobService quartzJobService;

    @Test
    void contextLoads() {
    }

    @Test
    @Transactional
    @Rollback(false)
    void addJob(){
        QuartzJob quartzJob=QuartzJob.builder()
                .beanName("orderQuartz")
                .jobName("TASK_")
                .methodName("closeOrder")
                .params(JSONUtil.toJsonStr(Order.builder()
                        .orderNumber(RandomUtil.randomNumbers(18))
                        .build()))
                .cronExpression("30 * * * * ?")
                .isPause(false)
                .createTime(LocalDateTime.now())
                .remark("order....")
                .build();
        quartzJobService.create(quartzJob);
    }
}
