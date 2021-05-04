package net.avanishkpandey.universum.continentservice.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.domain.model.Continent;
import net.avanishkpandey.universum.continentservice.repository.ContinentRepository;
import net.avanishkpandey.universum.continentservice.repository.RegionRepository;

@Service
public class RegionService {
	@Autowired
	private ContinentRepository continentRepository;

	@Autowired
	private RegionRepository regionRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<RegionResponse> findAllRegions() {
		return Optional.of(regionRepository.findAll()).orElseGet(Collections::emptyList).stream().map(RegionResponse.of)
				.collect(Collectors.toList());
	}

	public RegionResponse findById(final Long regionId) {
		return regionRepository.findById(regionId).map(RegionResponse.of)
				.orElseThrow(() -> new EntityNotFoundException("No region found."));
	}

	public List<RegionResponse> findRegionsByContinentId(final Long continentId) {
		Continent continent = continentRepository.findById(continentId)
				.orElseThrow(() -> new EntityNotFoundException("No continent found."));
		return Optional.of(regionRepository.findByContinentId(continent.getId())).orElseGet(Collections::emptyList)
				.stream().map(RegionResponse.of).collect(Collectors.toList());
	}

	/**
	 * private Object buildCacheKey(final Long identifier) {
	 * SessionFactoryImplementor sessionFactory =
	 * entityManager.getEntityManagerFactory().unwrap(SessionFactoryImplementor.class);
	 * EntityPersister entityPersister =
	 * ((MetamodelImplementor)entityManager.getEntityManagerFactory().unwrap(SessionFactory.class).getMetamodel()).entityPersister(Region.class.getName());
	 * 
	 * Object cacheKey = null; if(entityPersister.canReadFromCache()) { cacheKey =
	 * DefaultCacheKeysFactory.staticCreateEntityKey(identifier, entityPersister,
	 * sessionFactory, null); } return cacheKey; }
	 **/
}
