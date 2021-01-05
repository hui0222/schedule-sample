package com.example.schedule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

// app.scheduling.enable 값이 true 이면 설정하고, 없으면 true 유지, false 이면 실행안함.
@ConditionalOnProperty(value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true)
@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setErrorHandler(t -> {
            log.error("ThreadPoolTaskScheduler Error!", t);
            //  slackService.postMessage(true, "[에러확인요망] ThreadPoolTaskScheduler Error!\nerror: " + t.getMessage());
        });
        taskScheduler.initialize();

        taskRegistrar.setScheduler(taskScheduler);
    }
}
