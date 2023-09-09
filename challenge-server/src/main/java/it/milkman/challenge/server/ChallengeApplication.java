package it.milkman.challenge.server;

import it.milkman.challenge.dao.DummyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "it.milkman.challenge")
public class ChallengeApplication {

    private final DummyService dummyService;

    public ChallengeApplication(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }
}
