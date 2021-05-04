package net.avanishkpandey.universum.continentservice.repository.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import net.avanishkpandey.universum.continentservice.util.SearchCriteria;
import net.avanishkpandey.universum.continentservice.util.SearchOperation;

public final class SpecificationBuilder<T> {
	private static final String OPERATION_SET = String.join("|", SearchOperation.SIMPLE_OPERATION_SET);
	private static final Pattern REGEX_PATTERN = Pattern
			.compile("(\\w+?)(" + OPERATION_SET + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");

	private final List<SearchCriteria> searchCriteriaList;

	public SpecificationBuilder() {
		this.searchCriteriaList = new ArrayList<>();
	}

	public final SpecificationBuilder<T> with(final String queryString) {
		Matcher matcher = REGEX_PATTERN.matcher(queryString + ",");
		while (matcher.find()) {
			this.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5));
		}
		return this;
	}

	public final SpecificationBuilder<T> with(final String key, final String operator, final String prefix,
			final Object value, final String suffix) {
		SearchOperation searchOperation = SearchOperation.getSimpleOperation(operator.charAt(0));
		if (searchOperation != null) {
			searchOperation = checkSearchOperationForEquality(searchOperation, prefix, suffix);
			searchCriteriaList.add(new SearchCriteria(key, searchOperation, value));
		}
		return this;
	}

	public Specification<T> build() {
		Specification<T> result = null;
		if (!CollectionUtils.isEmpty(searchCriteriaList)) {
			List<Specification<T>> specifications = searchCriteriaList.stream().map(this::buildSpecification)
					.collect(Collectors.toList());
			result = specifications.get(0);
			for (int i = 1; i < searchCriteriaList.size(); i++) {
				result = Specification.where(result).and(specifications.get(i));
			}
		}
		return result;
	}

	protected SearchOperation checkSearchOperationForEquality(final SearchOperation searchOperation,
			final String prefix, final String suffix) {
		SearchOperation op = searchOperation;
		if (op == SearchOperation.EQUALITY || op == SearchOperation.NEGATION) {
			final boolean startWithAsterisk = StringUtils.hasText(prefix)
					&& prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
			final boolean endWithAsterisk = StringUtils.hasText(suffix)
					&& suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
			if (startWithAsterisk && endWithAsterisk)
				op = SearchOperation.CONTAINS;
			else if (startWithAsterisk)
				op = SearchOperation.ENDS_WITH;
			else if (endWithAsterisk)
				op = SearchOperation.STARTS_WITH;
		}
		return op;
	}

	protected Specification<T> buildSpecification(final SearchCriteria searchCriteria) {
		return (root, query, criteriaBuilder) -> buildCriteria(searchCriteria, root, criteriaBuilder);
	}

	protected Predicate buildCriteria(final SearchCriteria searchCriteria, Root<?> root,
			CriteriaBuilder criteriaBuilder) {
		switch (searchCriteria.getOperation()) {
		case EQUALITY:
			return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
		case NEGATION:
			return criteriaBuilder.notEqual(root.get(searchCriteria.getKey()), searchCriteria.getValue());
		case LIKE:
			return criteriaBuilder.like(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
		case CONTAINS:
			return criteriaBuilder.like(root.get(searchCriteria.getKey()),
					"%" + searchCriteria.getValue().toString() + "%");
		case STARTS_WITH:
			return criteriaBuilder.like(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString() + "%");
		case ENDS_WITH:
			return criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue().toString());
		case GREATER_THAN:
			return criteriaBuilder.greaterThan(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
		case LESS_THAN:
			return criteriaBuilder.lessThan(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
		case NOT_CONTAINS:
			return criteriaBuilder.notLike(root.get(searchCriteria.getKey()), searchCriteria.getValue().toString());
		default:
			return null;
		}
	}
}
