package net.avanishkpandey.universum.continentservice.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import net.avanishkpandey.universum.continentservice.ApplicationPostgresqlContainer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = { ApplicationPostgresqlContainer.Initializer.class })
class AbstractRepositoryTest {
	@ClassRule
	public static PostgreSQLContainer<?> postgreSQLContainer = ApplicationPostgresqlContainer.getInstance();
	
	
	@Test
	void whenSelectQueryExecuted_thenResulstsReturned() throws Exception {
	    String jdbcUrl = postgreSQLContainer.getJdbcUrl();
	    String username = postgreSQLContainer.getUsername();
	    String password = postgreSQLContainer.getPassword();
	    Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
	    ResultSet resultSet = conn.createStatement().executeQuery("SELECT 1");
	    resultSet.next();
	    int result = resultSet.getInt(1);
	    
	    resultSet.close();
	    conn.close();
	    Assert.assertEquals(1, result);
	}
}
