package com.thoughtworks.online_exam;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OnlineExamApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OnlineExamApplication.class)
            .bannerMode(Banner.Mode.OFF)
            .run(args);
    }
}
