package net.avanishkpandey.universum.continentservice.web.controller;

import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryLanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.service.CountryService;
import net.avanishkpandey.universum.continentservice.util.Chronometer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "countries")
public class CountryController {
    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @GetMapping
    public SearchPageResponse<CountryResponse> findAll(final SearchPageRequest searchPageRequest) {
        Chronometer chronometer = Chronometer.start();
        SearchPageResponse<CountryResponse> countries = countryService.findAllCountries(searchPageRequest);
        log.info("{} entities retrieved in {} MS.", countries.getContents().size(), chronometer.stop());
        return countries;
    }

    @GetMapping("/{countryId}")
    public CountryResponse findById(@PathVariable Long countryId) {
        Chronometer chronometer = Chronometer.start();
        CountryResponse country = countryService.findById(countryId);
        log.info("Find country by primary key {} in {} MS.", countryId, chronometer.stop());
        return country;
    }

    @GetMapping("/{countryId}/languages")
    public List<CountryLanguageResponse> findAllLanguagesByCountry(@PathVariable Long countryId) {
        return countryService.findCountryLanguages(countryId);
    }
}
