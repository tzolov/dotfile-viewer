package org.apache.crunch.dotfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class DotfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(DotfileApplication.class, args);
    }
}
