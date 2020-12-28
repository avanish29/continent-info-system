package net.avanishkpandey.universum.continentservice.service;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import net.avanishkpandey.universum.continentservice.dto.RegionDTO;
import net.avanishkpandey.universum.continentservice.entity.Region;
import net.avanishkpandey.universum.continentservice.repository.RegionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.internal.DefaultCacheKeysFactory;
import org.hibernate.cache.spi.CacheKeysFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.metamodel.spi.MetamodelImplementor;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private Config config;

    @PersistenceContext
    private EntityManager entityManager;

    public List<RegionDTO> findAllRegions() {
        return Optional.of(regionRepository.findAll()).orElseGet(Collections::emptyList).stream()
                .map(RegionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public RegionDTO findById(final Long regionId) {
        return regionRepository.findById(regionId)
                .map(RegionDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No region found."));
    }

    public List<RegionDTO> findRegionsByContinentId(final Long continentId) {
        return Optional.of(regionRepository.findByContinentId(continentId)).orElseGet(Collections::emptyList).stream()
                .map(RegionDTO::fromEntity)
                .collect(Collectors.toList());
    }

    private Object buildCacheKey(final Long identifier) {
        SessionFactoryImplementor sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactoryImplementor.class);
        EntityPersister entityPersister = ((MetamodelImplementor)entityManager.getEntityManagerFactory().unwrap(SessionFactory.class).getMetamodel()).entityPersister(Region.class.getName());

        Object cacheKey = null;
        if(entityPersister.canReadFromCache()) {
            cacheKey = DefaultCacheKeysFactory.staticCreateEntityKey(identifier, entityPersister, sessionFactory, null);
        }
        return cacheKey;
    }
}
