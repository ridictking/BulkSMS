package com.esd.sercom.bulksms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableCaching
public class BulksmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BulksmsApplication.class, args);
	}

}
