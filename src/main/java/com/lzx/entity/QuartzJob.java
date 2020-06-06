package com.lzx.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_quartz_job")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuartzJob {
    public static final String JOB_KEY = "JOB_KEY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 定时器名称 */
    @Column(name = "job_name")
    private String jobName;

    /** Bean名称 */
    @Column(name = "bean_name")
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
    @Column(name = "is_pause")
    private Boolean isPause = false;

    /** 备注 */
    @Column(name = "remark")
    private String remark;

    @Column(name = "create_time")
    @CreatedDate
    private LocalDateTime createTime;

}
