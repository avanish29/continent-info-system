package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.LanguageDTO;
import net.avanishkpandey.universum.continentservice.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LanguageDTO> findAll() {
        return languageService.findAllLanguages();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{languageId}")
    public LanguageDTO findById(@PathVariable Long languageId) {
        return languageService.findLanguageByID(languageId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/name/{languageName}")
    public LanguageDTO findByName(@PathVariable String languageName) {
        return languageService.findLanguageByName(languageName);
    }
}
