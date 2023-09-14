package it.milkman.challenge.server.service;

import it.milkman.challenge.server.ChallengeApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest(classes = ChallengeApplication.class)
@Profile("h2")
public class OrderServiceTests {
}
