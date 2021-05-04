package net.avanishkpandey.universum.continentservice.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import net.avanishkpandey.universum.continentservice.TestDataBuilder;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.RegionResponse;
import net.avanishkpandey.universum.continentservice.service.CountryService;
import net.avanishkpandey.universum.continentservice.service.RegionService;

@WebMvcTest(RegionController.class)
class RegionControllerTest {
	private static final String REGIONS_API_PATH = "/regions";
	private static final String REGION_BY_ID_API_PATH = REGIONS_API_PATH + "/{regionId}";
	private static final String COUNTRIES_BY_REGION_API_PATH = REGION_BY_ID_API_PATH + "/countries";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RegionService regionService;

	@MockBean
	private CountryService countryService;

	@Test
	void shouldReturnAllRegions() throws Exception {
		Mockito.when(regionService.findAllRegions()).thenReturn(TestDataBuilder.buildRegions());

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(REGIONS_API_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Caribbean")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].area", Matchers.is(234423.00)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].continent", Matchers.is("North America")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].countries", Matchers.notNullValue()));
	}

	@Test
	void shouldReturnRegionWithId() throws Exception {
		final Long regionToLookFor = Long.valueOf(14);
		List<RegionResponse> regions = TestDataBuilder.buildRegions();
		Mockito.when(regionService.findById(regionToLookFor))
				.thenReturn(regions.stream()
						.filter(regionDTO -> regionDTO.getId().equals(regionToLookFor))
						.findFirst()
						.orElseThrow(() -> new EntityNotFoundException("No region found.")));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(REGION_BY_ID_API_PATH, regionToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(regionToLookFor.intValue())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Central America")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.area", Matchers.is(2479532.00)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.continent", Matchers.is("North America")));
	}

	@Test
	void shouldReturnErrorWhenRegionIsNotPresent() throws Exception {
		final Long regionToLookFor = Long.valueOf(200);
		Mockito.when(regionService.findById(regionToLookFor))
				.thenThrow(new EntityNotFoundException("No region found."));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(REGION_BY_ID_API_PATH, regionToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("NOT_FOUND")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.timestamp", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("No region found.")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(404)));
	}

	@Test
	void shouldReturnAllCountriesForRegion() throws Exception {
		final Long regionToLookFor = Long.valueOf(1);
		RegionResponse region = TestDataBuilder.buildRegions().stream()
				.filter(regionDTO -> regionDTO.getId().equals(regionToLookFor)).findFirst().get();
		List<CountryResponse> countries = TestDataBuilder.buildCountries().stream()
				.filter(countryDTO -> countryDTO.getRegion().equalsIgnoreCase(region.getName()))
				.collect(Collectors.toList());
		Mockito.when(countryService.findCountriesByRegionId(regionToLookFor)).thenReturn(countries);

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(COUNTRIES_BY_REGION_API_PATH, regionToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(5)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Anguilla")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].region", Matchers.is("Caribbean")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].continent", Matchers.is("North America")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].area", Matchers.is(96.00)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].nationalDay", Matchers.is("1967-05-30")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].alpha2Code", Matchers.is("AI")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1].alpha3Code", Matchers.is("AIA")));
	}
}
