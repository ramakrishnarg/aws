package com.mycomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextCredentials;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

@SpringBootApplication
@EnableContextCredentials
public class Awss3demoApplicationConfig {

	private static final String ACCESS_KEY = "AKIAIJQ7BS53QEP22I4A";

	private static final String SECRET_KEY = "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";

	public static void main(String[] args) {
		SpringApplication.run(Awss3demoApplicationConfig.class, args);
	}

	@Bean
	public AmazonS3Client amazonS3Client() {
		return new AmazonS3Client(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
	}
}
