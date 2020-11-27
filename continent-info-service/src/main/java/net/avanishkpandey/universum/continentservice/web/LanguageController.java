package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.LanguageDTO;
import net.avanishkpandey.universum.continentservice.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<LanguageDTO>> findAll() {
        return new ResponseEntity<List<LanguageDTO>>(languageService.findAllLanguages(), HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.GET, path = "/{languageId}")
    public ResponseEntity<LanguageDTO> findById(@PathVariable Long languageId) {
        try {
            return new ResponseEntity<LanguageDTO>(languageService.findLanguageByID(languageId), HttpStatus.OK);
        } catch(EntityNotFoundException enfEx) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language Not Found !!", enfEx);
        }
    }
}
