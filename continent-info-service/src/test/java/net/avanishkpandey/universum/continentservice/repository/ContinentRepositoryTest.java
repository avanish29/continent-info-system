package net.avanishkpandey.universum.continentservice.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.avanishkpandey.universum.continentservice.domain.model.Continent;

class ContinentRepositoryTest extends AbstractRepositoryTest {
	@Autowired
	private ContinentRepository continentRepository;

	@Test
	void shouldReturnContinentCount() {
		Assert.assertEquals(7, continentRepository.findAll().size());
	}

	@Test
	void shouldReturnAllContientWithGraph() {
		List<Continent> continents = continentRepository.findByIdNotNull();

		Assert.assertNotNull(continents);
		Assert.assertEquals(7, continents.size());

		Assert.assertNotNull(continents.get(0).getRegions());
		Assert.assertEquals(3, continents.get(0).getRegions().size());
	}
}
