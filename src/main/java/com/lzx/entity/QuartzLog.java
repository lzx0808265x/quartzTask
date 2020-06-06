package com.lzx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_quartz_log")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartzLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 任务名称 */
    @Column(name = "job_name")
    private String jobName;

    /** Bean名称 */
    @Column(name = "baen_name")
    private String beanName;

    /** 方法名称 */
    @Column(name = "method_name")
    private String methodName;

    /** 参数 */
    @Column(name = "params")
    private String params;

    /** cron表达式 */
    @Column(name = "cron_expression")
    private String cronExpression;

    /** 状态 */
    @Column(name = "is_success")
    private Boolean isSuccess;

    /** 异常详细 */
    @Column(name = "exception_detail",columnDefinition = "text")
    private String exceptionDetail;

    /** 耗时（毫秒） */
    private Long time;

    /** 创建日期 */
    @CreatedDate
    @Column(name = "create_time")
    private LocalDateTime createTime;
}
