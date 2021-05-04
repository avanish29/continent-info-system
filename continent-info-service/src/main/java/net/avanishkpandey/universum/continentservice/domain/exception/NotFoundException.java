package net.avanishkpandey.universum.continentservice.domain.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = -8040677347814231193L;

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Class<?> clazz, long id) {
		super(String.format("Entity '%s' with id '%d' not found", clazz.getSimpleName(), id));
	}

	public NotFoundException(Class<?> clazz, String id) {
		super(String.format("Entity '%s' with id '%s' not found", clazz.getSimpleName(), id));
	}
}
