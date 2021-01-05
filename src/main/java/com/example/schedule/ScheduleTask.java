package com.example.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class ScheduleTask {

    //초 분 시 일 월 요일
    //(5 * * * * MON) -> 월요일 5초
    //("20 0-50/10 3 1-16/15 * *")
    // 매달 1일, 16일 | 03시부터 | 0~50분 10분간격으로 | 20초
    @Scheduled(cron = "5 * * * * *")
    public void exampleSchedule(){
        Instant start = Instant.now();
        System.out.println("start" + start);
        System.out.println("end /" + Duration.between(start, Instant.now()).toMillis());
    }
}
