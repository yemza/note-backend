package com.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.note.security.RsakeysConfig;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class Application {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}
}
