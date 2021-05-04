package net.avanishkpandey.universum.continentservice.repository.impl;

import net.avanishkpandey.universum.continentservice.domain.model.Country;
import net.avanishkpandey.universum.continentservice.repository.BaseGraphRepository;
import net.avanishkpandey.universum.continentservice.util.GraphName;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepositoryImpl implements BaseGraphRepository<Country, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Country> findAllWithGraphName(final GraphName graphName) {
        return entityManager.createNamedQuery(Country.FIND_ALL_COUNTRY, Country.class)
                .setHint(EntityGraph.EntityGraphType.FETCH.getKey(),
                        entityManager.getEntityGraph(graphName.getName()))
                .getResultList();
    }

    @Override
    @Cacheable(value = "COUNTRY_CACHE_REGION", key = "#identity")
    public Optional<Country> findByIdWithGraphName(final Long identity, final GraphName graphName) {
        return Optional.ofNullable(entityManager.find(Country.class, identity,
                Collections.singletonMap(EntityGraph.EntityGraphType.FETCH.getKey(),
                        entityManager.getEntityGraph(graphName.getName()))));
    }
}
