package com.deloitte.integ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigManagerApplication.class, args);
	}
}
