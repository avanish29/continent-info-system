package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.CountryDTO;
import net.avanishkpandey.universum.continentservice.dto.LanguageDTO;
import net.avanishkpandey.universum.continentservice.service.CountryService;
import net.avanishkpandey.universum.continentservice.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private LanguageService languageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<CountryDTO> findAll() {
        return countryService.findAllCountries();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{countryId}")
    public CountryDTO findById(@PathVariable Long countryId) {
        return countryService.findById(countryId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{countryId}/languages")
    public List<LanguageDTO> findAllLanguagesByCountry(@PathVariable Long countryId) {
        return languageService.findAllLanguagesByCountry(countryId);
    }
}
