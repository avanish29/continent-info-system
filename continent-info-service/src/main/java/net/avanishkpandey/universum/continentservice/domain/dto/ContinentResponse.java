package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Relation(itemRelation = "continent", collectionRelation = "continents")
public class ContinentResponse extends RepresentationModel<ContinentResponse> implements Serializable {
	private static final long serialVersionUID = -5982975500329506921L;
	private Long id;
	private String name;
}
