package com.api.djobs;

import com.api.djobs.oauth.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableConfigurationProperties({AppProperties.class})
public class DJobsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DJobsApplication.class, args);
	}

}
