package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.ContinentDTO;
import net.avanishkpandey.universum.continentservice.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/continents")
public class ContinentController {
    @Autowired
    private ContinentService continentService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<ContinentDTO>> findAll() {
        return new ResponseEntity<List<ContinentDTO>>(continentService.findAllContinents(), HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.GET, path = "/{continentId}")
    public ResponseEntity<ContinentDTO> findById(@PathVariable Long continentId) {
        try {
            return new ResponseEntity<ContinentDTO>(continentService.findContinentByID(continentId), HttpStatus.OK);
        } catch(EntityNotFoundException enfEx) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Continent Not Found !!", enfEx);
        }
    }
}
