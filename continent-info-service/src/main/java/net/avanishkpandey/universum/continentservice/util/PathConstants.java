package net.avanishkpandey.universum.continentservice.util;

public final class PathConstants {
	private PathConstants() {
		throw new AssertionError("Cannot create instance !");
	}
	
	private static final String SLASH = "/";
	
	public static final String CONTINENTS = SLASH + "continents";
	public static final String CONTINENT_BY_ID = CONTINENTS + SLASH + "{continentId}";
	public static final String REGIONS_BY_CONTINENT = CONTINENT_BY_ID + SLASH + "regions";
	
	public static final String ATHLETES = SLASH + "athletes";
	public static final String ATHLETE_BY_ID = ATHLETES + SLASH + "{athleteId}";
	
	public static final String COUNTRIES = SLASH + "countries";
	public static final String COUNTRY_BY_ID = COUNTRIES + SLASH + "{countryId}";
	public static final String LANGUAGES_BY_COUNTRY = COUNTRY_BY_ID + SLASH + "languages";
	
	public static final String LANGUAGES = SLASH + "languages";
	public static final String LANGUAGE_BY_ID = LANGUAGES + SLASH + "{languageId}";
	
	public static final String REGIONS = SLASH + "regions";
	public static final String REGION_BY_ID = REGIONS + SLASH + "{regionId}";
	public static final String COUNTRIES_BY_REGION = REGION_BY_ID + SLASH + "countries";
}
