package net.avanishkpandey.universum.continentservice.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.avanishkpandey.universum.continentservice.domain.model.Region;

class RegionRepositoryTest extends AbstractRepositoryTest {
	@Autowired
	private RegionRepository regionRepository;

	@Test
	void shouldReturnAllRegionForContinent() {
		List<Region> regions = regionRepository.findByContinentId(Long.valueOf(2));

		Assert.assertNotNull(regions);
		Assert.assertEquals(4, regions.size());
	}

	@Test
	void shouldReturnEmptyRegionsListWhenContinentNotPresent() {
		List<Region> regions = regionRepository.findByContinentId(Long.valueOf(20));

		Assert.assertNotNull(regions);
		Assert.assertEquals(0, regions.size());
	}

	@Test
	void shouldReturnAllRegionForContinentByName() {
		List<Region> regions = regionRepository.findByContinentNameIgnoreCase("Europe");

		Assert.assertNotNull(regions);
		Assert.assertEquals(6, regions.size());
	}
}
