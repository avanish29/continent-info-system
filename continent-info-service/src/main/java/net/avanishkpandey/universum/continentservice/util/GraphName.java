package net.avanishkpandey.universum.continentservice.util;

public enum GraphName {
    CONTINENT_WITH_REGIONS_AND_COUNTRIES ("continent_regions_countries_language_statistics"),
    COUNTRY_WITH_LANGUAGE_AND_STATISTICS ("country_language_statistics");;

    private String name;

    private GraphName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
