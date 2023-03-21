package com.isoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.isoft.mapper")
@SpringBootApplication
public class FlightInfoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightInfoDemoApplication.class, args);
    }

}
