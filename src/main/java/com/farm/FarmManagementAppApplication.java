package com.farm;

import com.farm.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value= SwaggerConfig.class)
public class FarmManagementAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmManagementAppApplication.class, args);
    }
}
