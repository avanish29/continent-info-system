package net.avanishkpandey.universum.continentservice.repository;

import org.junit.ClassRule;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import net.avanishkpandey.universum.continentservice.ApplicationPostgresqlContainer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = { ApplicationPostgresqlContainer.Initializer.class })
public class AbstractRepositoryTest {
	@ClassRule
	public static PostgreSQLContainer<?> postgreSQLContainer = ApplicationPostgresqlContainer.getInstance();
}
