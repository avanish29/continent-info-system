package net.avanishkpandey.universum.continentservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;

@Data
@Builder
@Relation(collectionRelation = "languages", itemRelation = "language")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageDTO implements Serializable {
    private Long id;
    private String language;
    private String countryName;
    private Boolean official;
}
