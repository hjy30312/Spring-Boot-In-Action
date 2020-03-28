package com.hjy.springbootmybatisplus.controller;


import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log
public class ScheduleTask {
    
    // 表示方法执行完成后5秒
    @Scheduled(fixedDelay = 60000)
    public void test() {
        log.info("fixedDelay 1分钟：" + new Date());
    }

    // 表示每隔50秒执行
    @Scheduled(fixedRate = 50000)
    public void test2() {
        log.info("fixedRate 50秒 " + new Date());
    }

    // 每天0点十分执行
    @Scheduled(cron = "0 8 0 * * ?")
    public void cronTest() {
        log.info("cron 每天0点8分执行 " + new Date());
    }
}
