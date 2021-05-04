package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Relation(collectionRelation = "languages", itemRelation = "language")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageResponse implements Serializable {
	private static final long serialVersionUID = -1301225730396827875L;
	private Long id;
	private String language;
}
