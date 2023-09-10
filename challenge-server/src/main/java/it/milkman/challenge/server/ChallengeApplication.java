package it.milkman.challenge.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "it.milkman.challenge")
@EnableJpaRepositories(basePackages = "it.milkman.challenge")
@EntityScan(basePackages = "it.milkman.challenge")
@EnableTransactionManagement
@EnableJpaAuditing
public class ChallengeApplication {

    public ChallengeApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }
}
