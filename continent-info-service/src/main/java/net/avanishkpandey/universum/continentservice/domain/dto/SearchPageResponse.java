package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SearchPageResponse<D> implements Serializable {
	private static final long serialVersionUID = -5439361164774180661L;
	private long totalItems;
	private long totalPages;
	private long currentPage;
	@ToString.Exclude
	private List<D> contents;

	/**
	 * Creates a new {@link SearchPageResponse} with page result and converter
	 * function.
	 * 
	 * @param pageResult        - Paged result from database.
	 * @param converterFunction - {@link Function} to convert entity to model
	 * @param <D>               - Model type
	 * @param <E>               - Entity type
	 * @return - Instance of {@link SearchPageResponse}
	 */
	public static <D, E> SearchPageResponse<D> of(final Page<E> pageResult, final Function<E, D> converterFunction) {
		List<D> contents = pageResult.stream().map(converterFunction).collect(Collectors.toList());
		return new SearchPageResponse<>(pageResult.getTotalElements(), pageResult.getTotalPages(),
				pageResult.getNumber(), contents);
	}

	public static <D> SearchPageResponse<D> of(final long totalItems, final long totalPages, final long currentPage,
			final List<D> contents) {
		return new SearchPageResponse<>(totalItems, totalPages, currentPage, contents);
	}
}
