package com.zxtcw.zxtcwlaunch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.zxtcw.*"})
//开启事务支持（service事务中添加@Transactional进行事务控制）
//@EnableTransactionManagement
@EnableScheduling
public class ZxtcwLaunchApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZxtcwLaunchApplication.class, args);
    }

}
