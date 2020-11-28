--public.hibernate_sequence definition
CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence;

--public.continents definition
CREATE TABLE IF NOT EXISTS public.continents (
	continent_id bigint NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT continents_pk PRIMARY KEY (continent_id)
);

--public.regions definition
CREATE TABLE IF NOT EXISTS public.regions (
  region_id bigint NOT NULL,
  "name" varchar(100) NOT NULL,
  region_area numeric NOT NULL,
  continent_id bigint NOT NULL,
  CONSTRAINT regions_pk PRIMARY KEY (region_id)
);
ALTER TABLE public.regions ADD CONSTRAINT regions_fk FOREIGN KEY (continent_id) REFERENCES continents(continent_id);

-- public.languages definition
CREATE TABLE IF NOT EXISTS public.languages (
  language_id bigint NOT NULL,
  "language" varchar(50) NOT NULL,
  CONSTRAINT languages_pk PRIMARY KEY (language_id)
);

-- public.countries definition
CREATE TABLE IF NOT EXISTS public.countries (
	country_id bigint NOT NULL,
	"name" varchar(255) NOT NULL,
	area numeric NOT NULL,
    national_day date NULL,
    country_code2 varchar(2) NOT NULL,
    country_code3 varchar(3) NOT NULL,
    region_id bigint NOT NULL,
    CONSTRAINT countries_pk PRIMARY KEY (country_id),
    CONSTRAINT country_code2 UNIQUE (country_code2),
    CONSTRAINT country_code3 UNIQUE (country_code3)
);
ALTER TABLE public.countries ADD CONSTRAINT countries_fk FOREIGN KEY (region_id) REFERENCES regions(region_id);

-- public.country_languages definition
CREATE TABLE IF NOT EXISTS public.country_languages (
	country_id bigint NOT NULL,
	language_id bigint NOT NULL,
	official bool NOT NULL,
	CONSTRAINT country_languages_pk PRIMARY KEY (country_id, language_id)
);
ALTER TABLE public.country_languages ADD CONSTRAINT country_languages_fk FOREIGN KEY (country_id) REFERENCES countries(country_id);
ALTER TABLE public.country_languages ADD CONSTRAINT country_languages_fk1 FOREIGN KEY (language_id) REFERENCES languages(language_id);

-- public.country_stats definition
CREATE TABLE IF NOT EXISTS public.country_stats (
	country_id bigint NOT NULL,
	"year" int4 NOT NULL,
	population bigint NULL,
	gdp numeric NULL,
	CONSTRAINT country_stats_pk PRIMARY KEY (country_id, year)
);
ALTER TABLE public.country_stats ADD CONSTRAINT country_stats_fk FOREIGN KEY (country_id) REFERENCES countries(country_id);