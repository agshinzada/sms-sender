package com.smsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApiDbApplication {

	@Bean
	public ThreadPoolTaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTask");
		return threadPoolTaskScheduler;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiDbApplication.class, args);
	}

}
