package net.avanishkpandey.universum.continentservice.repository.impl;

import net.avanishkpandey.universum.continentservice.entity.Continent;
import net.avanishkpandey.universum.continentservice.repository.BaseGraphRepository;
import net.avanishkpandey.universum.continentservice.util.GraphName;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ContinentRepositoryImpl implements BaseGraphRepository<Continent, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Continent> findAllWithGraphName(final GraphName graphName) {
        return entityManager.createNamedQuery(Continent.FIND_ALL_CONTINENT, Continent.class)
                .setHint(EntityGraph.EntityGraphType.FETCH.getKey(),
                        entityManager.getEntityGraph(graphName.getName()))
                .getResultList();
    }

    @Override
    public Optional<Continent> findByIdWithGraphName(final Long identity, final GraphName graphName) {
        return Optional.ofNullable(entityManager.find(Continent.class, identity,
                Collections.singletonMap(EntityGraph.EntityGraphType.FETCH.getKey(),
                        entityManager.getEntityGraph(graphName.getName()))));
    }
}
