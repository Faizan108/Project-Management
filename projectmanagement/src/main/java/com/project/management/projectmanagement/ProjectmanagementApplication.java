package com.project.management.projectmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.project.management.repository")
@EntityScan("com.project.*")
@ComponentScan(basePackages ={ "com.project.management"})
@SpringBootApplication
public class ProjectmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectmanagementApplication.class, args);
	}

}
