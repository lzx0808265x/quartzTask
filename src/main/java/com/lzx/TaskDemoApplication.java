package com.lzx;

import com.lzx.holder.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class TaskDemoApplication {

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskDemoApplication.class, args);
    }

}
