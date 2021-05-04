package net.avanishkpandey.universum.continentservice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import net.avanishkpandey.universum.continentservice.domain.dto.ContinentResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryLanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryStatsResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.LanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.web.controller.ContinentController;

public class TestDataBuilder {

    public static List<ContinentResponse> buildContinents() {
        return List.of(buildContinentLinks(new ContinentResponse(Long.valueOf(1), "North America")),
        		buildContinentLinks(new ContinentResponse(Long.valueOf(2), "Asia")),
        		buildContinentLinks(new ContinentResponse(Long.valueOf(3), "Africa")),
        		buildContinentLinks(new ContinentResponse(Long.valueOf(4), "Europe")),
        		buildContinentLinks(new ContinentResponse(Long.valueOf(5), "South America")),
        		buildContinentLinks(new ContinentResponse(Long.valueOf(6), "Oceania")),
        		buildContinentLinks(new ContinentResponse(Long.valueOf(7), "Antarctica")));
    }
    

    public static List<RegionResponse> buildRegions() {
        return List.of(new RegionResponse(Long.valueOf(1), "Caribbean", BigDecimal.valueOf(234423.00), "North America", buildCountries()),
                new RegionResponse(Long.valueOf(14), "Central America", BigDecimal.valueOf(2479532.00), "North America", null),
                new RegionResponse(Long.valueOf(15), "North America", BigDecimal.valueOf(21500515.00), "North America", null));
    }

    public static List<CountryResponse> buildCountries() {
        return List.of(new CountryResponse(Long.valueOf(1), "Aruba", "Caribbean", "North America", BigDecimal.valueOf(193.00), null, "AW", "ABW", buildCountryLanguages(), buildCountryStats()),
                new CountryResponse(Long.valueOf(2), "Anguilla", "Caribbean", "North America", BigDecimal.valueOf(96.00), LocalDate.parse("1967-05-30"), "AI", "AIA", null, null),
                new CountryResponse(Long.valueOf(3), "Netherlands Antilles", "Caribbean", "North America", BigDecimal.valueOf(800.00), null, "AN", "ANT", null, null),
                new CountryResponse(Long.valueOf(4), "Antigua and Barbuda", "Caribbean", "North America", BigDecimal.valueOf(442.00), LocalDate.parse("1981-11-01"), "AG", "ATG", null, null),
                new CountryResponse(Long.valueOf(5), "Bahamas", "Caribbean", "North America", BigDecimal.valueOf(13878.00), null, "BS", "BHS", null, null));
    }

    public static Set<CountryLanguageResponse> buildCountryLanguages() {
        return Set.of(new CountryLanguageResponse("Dutch", true),
                new CountryLanguageResponse("English", false),
                new CountryLanguageResponse("Papiamento", false),
                new CountryLanguageResponse("Spanish", false));
    }

    public static Set<CountryStatsResponse> buildCountryStats() {
        return Set.of(new CountryStatsResponse(Integer.valueOf(1986), Long.valueOf(62644), BigDecimal.valueOf(405463417)),
                new CountryStatsResponse(Integer.valueOf(1987), Long.valueOf(61833), BigDecimal.valueOf(487602457)),
                new CountryStatsResponse(Integer.valueOf(1988), Long.valueOf(61079), BigDecimal.valueOf(596423607)),
                new CountryStatsResponse(Integer.valueOf(1989), Long.valueOf(61032), BigDecimal.valueOf(695304363)),
                new CountryStatsResponse(Integer.valueOf(1990), Long.valueOf(62149), BigDecimal.valueOf(764887117)));
    }

    public static List<LanguageResponse> buildLanguages() {
        return List.of(new LanguageResponse(Long.valueOf(1), "Dutch"), new LanguageResponse(Long.valueOf(2), "English"), new LanguageResponse(Long.valueOf(3), "Papiamento"), new LanguageResponse(Long.valueOf(4), "Spanish"),
                new LanguageResponse(Long.valueOf(5), "Balochi"), new LanguageResponse(Long.valueOf(6), "Dari"), new LanguageResponse(Long.valueOf(7), "Pashto"), new LanguageResponse(Long.valueOf(8), "Turkmenian"),
                new LanguageResponse(Long.valueOf(9), "Uzbek"), new LanguageResponse(Long.valueOf(10), "Ambo"), new LanguageResponse(Long.valueOf(11), "Chokwe"), new LanguageResponse(Long.valueOf(12), "Kongo"),
                new LanguageResponse(Long.valueOf(13), "Luchazi"), new LanguageResponse(Long.valueOf(14), "Luimbe-nganguela"), new LanguageResponse(Long.valueOf(15), "Luvale"), new LanguageResponse(Long.valueOf(16), "Mbundu"),
                new LanguageResponse(Long.valueOf(17), "Nyaneka-nkhumbi"), new LanguageResponse(Long.valueOf(18), "Albaniana"), new LanguageResponse(Long.valueOf(19), "Greek"), new LanguageResponse(Long.valueOf(20), "Macedonian"));
    }
    
    private static ContinentResponse buildContinentLinks(final ContinentResponse response) {
    	if(response != null) {
    		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContinentController.class).findById(response.getId())).withSelfRel());
    		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ContinentController.class).findAllRegionsByContinent(response.getId())).withRel("regions"));
    	}
    	return response;
    }
}
