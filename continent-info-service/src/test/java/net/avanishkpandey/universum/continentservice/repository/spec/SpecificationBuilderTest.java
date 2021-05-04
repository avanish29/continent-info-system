package net.avanishkpandey.universum.continentservice.repository.spec;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import net.avanishkpandey.universum.continentservice.domain.model.Athlete;

class SpecificationBuilderTest {

	@Test
	void shouldParseQuery() {
		Specification<Athlete> querySpecification = new SpecificationBuilder<Athlete>().with("fullName:*Gocic").build();

		Assert.assertNotNull(querySpecification);
	}
}
