package net.avanishkpandey.universum.continentservice.domain.dto;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class SearchPageRequest implements Serializable {
	private static final long serialVersionUID = -6968153208033960754L;
	private String query = ""; // Default to empty
	private int page = 0; // Default to first page
	private int size = 10; // Default to 10 rows per page
	private String sortBy = "id"; // Default to primary key 'ID'
	private String order = "ASC"; // Default to ASCENDING order

	public SearchPageRequest() {
	}

	public SearchPageRequest(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public Sort.Direction sortDirection() {
		Optional<Sort.Direction> direction = Sort.Direction.fromOptionalString(this.getOrder());
		return direction.isPresent() ? direction.get() : Sort.Direction.ASC;
	}

	public Pageable pageable() {
		return PageRequest.of(this.getPage(), this.getSize(),
				Sort.by(new Sort.Order(sortDirection(), sortBy).ignoreCase()));
	}
}
