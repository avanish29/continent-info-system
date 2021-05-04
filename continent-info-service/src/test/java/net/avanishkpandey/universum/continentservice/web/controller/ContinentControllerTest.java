package net.avanishkpandey.universum.continentservice.web.controller;

import java.util.List;

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
import net.avanishkpandey.universum.continentservice.domain.dto.ContinentResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.service.ContinentService;

@WebMvcTest(ContinentController.class)
class ContinentControllerTest {
	private static final String CONTINENTS_API_PATH = "/continents";
	private static final String CONTINENT_BY_ID_API_PATH = CONTINENTS_API_PATH + "/{continent_id}";
	private static final String REGIONS_BY_CONTINENT_ID_API_PATH = CONTINENT_BY_ID_API_PATH + "/regions";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ContinentService continentService;

	@Test
	void shouldReturnAllContinents() throws Exception {
		List<ContinentResponse> continents = TestDataBuilder.buildContinents();
		Mockito.when(continentService.findAllContinents(new SearchPageRequest()))
				.thenReturn(SearchPageResponse.of(continents.size(), 1, 0, continents));

		this.mockMvc.perform(MockMvcRequestBuilders.get(CONTINENTS_API_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalItems", Matchers.is(7)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", Matchers.is(0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents.length()", Matchers.is(7)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].name", Matchers.is("North America")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].links.length()", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].links[0].rel", Matchers.is("self")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].links[0].href", Matchers.is("/continents/1")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].links[1].rel", Matchers.is("regions")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].links[1].href", Matchers.is("/continents/1/regions")));

	}

	@Test
	void shouldReturnContinentById() throws Exception {
		final Long continentToLookFor = Long.valueOf(2);
		Mockito.when(continentService.findContinentByID(continentToLookFor))
				.thenReturn(findContinentById(continentToLookFor));

		this.mockMvc.perform(MockMvcRequestBuilders.get(CONTINENT_BY_ID_API_PATH, continentToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Asia")))
				.andExpect(MockMvcResultMatchers.jsonPath("$._links.length()", Matchers.is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href", Matchers.is("/continents/2")))
				.andExpect(MockMvcResultMatchers.jsonPath("$._links.regions.href", Matchers.is("/continents/2/regions")));
	}

	@Test
	void shouldReturnErrorWhenContinentIdDoesNotExists() throws Exception {
		final Long continentToLookFor = Long.valueOf(20);
		Mockito.when(continentService.findContinentByID(Long.valueOf(continentToLookFor)))
				.thenThrow(new EntityNotFoundException("No continent found."));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(CONTINENT_BY_ID_API_PATH, continentToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("NOT_FOUND")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("No continent found.")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(404)));
	}


	@Test
	void shouldReturnRegionsForContinent() throws Exception {
		final Long continentToLookFor = Long.valueOf(1);
		Mockito.when(continentService.findRegionsByContinent(continentToLookFor))
				.thenReturn(TestDataBuilder.buildRegions());

		this.mockMvc.perform(MockMvcRequestBuilders.get(REGIONS_BY_CONTINENT_ID_API_PATH, continentToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(3)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Caribbean")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].countries.length()", Matchers.is(5)));
	}


	@Test
	void shouldReturnErrorWhenRegionContinentIsNotPresent() throws Exception {
		final Long continentToLookFor = Long.valueOf(20);
		Mockito.when(continentService.findRegionsByContinent(continentToLookFor))
				.thenThrow(new EntityNotFoundException("No continent found."));

		this.mockMvc.perform(MockMvcRequestBuilders.get(REGIONS_BY_CONTINENT_ID_API_PATH, continentToLookFor)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("NOT_FOUND")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("No continent found.")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(404)));
	}

	private ContinentResponse findContinentById(final Long continentId) {
		return TestDataBuilder.buildContinents()
							  .stream()
							  .filter(continentDTO -> continentDTO.getId().equals(continentId))
							  .findFirst()
							  .orElseThrow(() -> new EntityNotFoundException("No continent found."));
	}
}
