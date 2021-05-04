package net.avanishkpandey.universum.continentservice.web.controller;

import net.avanishkpandey.universum.continentservice.domain.dto.LanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<LanguageResponse> findAll(SearchPageRequest searchPageRequest) {
        return languageService.findAllLanguages(searchPageRequest);
    }

    @GetMapping("/{languageId}")
    public LanguageResponse findById(@PathVariable Long languageId) {
        return languageService.findLanguageByID(languageId);
    }
}
