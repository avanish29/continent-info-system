package net.avanishkpandey.universum.continentservice.repository;

import net.avanishkpandey.universum.continentservice.util.GraphName;

import java.util.List;
import java.util.Optional;

public interface BaseGraphRepository<D, T> {

    List<D> findAllWithGraphName(final GraphName graphName);

    Optional<D> findByIdWithGraphName(final T identity, final GraphName graphName);
}
