package com.vntechno.vntechno;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableMongoRepositories
public class VntechnoApplication {

	@Autowired
	private MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		System.out.println("hello world !");
		SpringApplication.run(VntechnoApplication.class, args);
	}

	@PostConstruct
	public void listenMongoDBConnect() {
		System.out.println("Checking MongoDB connection...");
		try {
			String dbName = mongoTemplate.getDb().getName();
			System.out.println("Successfully connected to the database");
			System.out.println("Database Name: " + dbName);
		} catch (Exception e) {
			System.err.println("Failed to connect to the database");
			e.printStackTrace();
		}
	}

}
