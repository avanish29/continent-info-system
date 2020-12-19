package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.CountryDTO;
import net.avanishkpandey.universum.continentservice.dto.LanguageDTO;
import net.avanishkpandey.universum.continentservice.dto.PageDTO;
import net.avanishkpandey.universum.continentservice.service.CountryService;
import net.avanishkpandey.universum.continentservice.service.LanguageService;
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

    @Autowired
    private LanguageService languageService;

    @RequestMapping(method = RequestMethod.GET)
    public PageDTO<CountryDTO> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Chronometer chronometer = Chronometer.start();
        PageDTO<CountryDTO> countries = countryService.findAllCountries(page, size);
        log.info("{} entities retrieved in {} MS.", countries.getContents().size(), chronometer.stop());
        return countries;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{countryId}")
    public CountryDTO findById(@PathVariable Long countryId) {
        Chronometer chronometer = Chronometer.start();
        CountryDTO country = countryService.findById(countryId);
        log.info("Find country by primary key {} in {} MS.", countryId, chronometer.stop());
        return country;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{countryId}/languages")
    public List<LanguageDTO> findAllLanguagesByCountry(@PathVariable Long countryId) {
        return languageService.findAllLanguagesByCountry(countryId);
    }
}
