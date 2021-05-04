package net.avanishkpandey.universum.continentservice.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import net.avanishkpandey.universum.continentservice.domain.dto.LanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.service.LanguageService;
import net.avanishkpandey.universum.continentservice.util.PathConstants;

@RestController
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping(PathConstants.LANGUAGES)
    public List<LanguageResponse> findAll(SearchPageRequest searchPageRequest) {
        return languageService.findAllLanguages(searchPageRequest);
    }

    @GetMapping(PathConstants.LANGUAGE_BY_ID)
    public LanguageResponse findById(@PathVariable Long languageId) {
        return languageService.findLanguageByID(languageId);
    }
}
