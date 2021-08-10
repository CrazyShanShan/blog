package org.example.config;

import org.example.task.ArticleTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * @Description: "定时"
 * @Author: ZBZ
 * @Date: 2021/8/10
 */
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuarts";


    @Bean
    public JobDetail quartzDetail(){
        return JobBuilder.newJob(ArticleTask.class).withIdentity(LIKE_TASK_IDENTITY).storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
               // .withIntervalInSeconds(60)  //设置时间周期单位秒
                .withIntervalInMinutes(30)
//                 .withIntervalInHours(2)  //两个小时执行一次
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }

}
