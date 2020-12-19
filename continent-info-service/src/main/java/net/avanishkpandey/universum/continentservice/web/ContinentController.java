package net.avanishkpandey.universum.continentservice.web;

import net.avanishkpandey.universum.continentservice.dto.ContinentDTO;
import net.avanishkpandey.universum.continentservice.dto.RegionDTO;
import net.avanishkpandey.universum.continentservice.service.ContinentService;
import net.avanishkpandey.universum.continentservice.service.RegionService;
import net.avanishkpandey.universum.continentservice.util.Chronometer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/continents")
public class ContinentController {
    private static final Logger log = LoggerFactory.getLogger(ContinentController.class);

    @Autowired
    private ContinentService continentService;

    @Autowired
    private RegionService regionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ContinentDTO> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Chronometer chronometer = Chronometer.start();
        List<ContinentDTO> allContinent = continentService.findAllContinents();
        log.info("{} entities retrieved in {} MS.}", allContinent.size(), chronometer.stop());
        return allContinent;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{continentId}")
    public ContinentDTO findById(@PathVariable Long continentId) {
        Chronometer chronometer = Chronometer.start();
        ContinentDTO continent = continentService.findContinentByID(continentId);
        log.info("Find continent by primary key {} in {} MS.", continentId, chronometer.stop());
        return continent;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{continentId}/regions")
    public List<RegionDTO> findAllRegionsByContinent(@PathVariable Long continentId) {
        return regionService.findRegionsByContinentId(continentId);
    }
}
