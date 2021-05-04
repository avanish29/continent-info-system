package net.avanishkpandey.universum.continentservice;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

public class ApplicationPostgresqlContainer extends PostgreSQLContainer<ApplicationPostgresqlContainer> {
    private static final String POSTGRE_IMAGE_VERSION = "postgres:13.1-alpine";
    private static final String POSTGRE_DATABASE_NAME = "nation-test";
    private static final String POSTGRE_DATABASE_USERNAME = "root";
    private static final String POSTGRE_DATABASE_PASSWORD = "root";
    private static ApplicationPostgresqlContainer INSTANCE;

    private ApplicationPostgresqlContainer() {
        super(POSTGRE_IMAGE_VERSION);
    }

    public static ApplicationPostgresqlContainer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationPostgresqlContainer()
                    .withDatabaseName(POSTGRE_DATABASE_NAME)
                    .withUsername(POSTGRE_DATABASE_USERNAME)
                    .withPassword(POSTGRE_DATABASE_PASSWORD);
            INSTANCE.start();
        }
        return INSTANCE;
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + INSTANCE.getJdbcUrl(),
                    "spring.datasource.username=" + INSTANCE.getUsername(),
                    "spring.datasource.password=" + INSTANCE.getPassword()
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}
