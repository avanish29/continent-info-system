package net.avanishkpandey.universum.continentservice.web.controller;

import java.util.Comparator;
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
import net.avanishkpandey.universum.continentservice.domain.dto.CountryLanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.CountryResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageResponse;
import net.avanishkpandey.universum.continentservice.service.CountryService;
import net.avanishkpandey.universum.continentservice.util.PathConstants;

@WebMvcTest(CountryController.class)
class CountryControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CountryService countryService;

	@Test
	void shouldReturnAllCountryWithDefaultPagination() throws Exception {
		List<CountryResponse> countries = TestDataBuilder.buildCountries();
		Mockito.when(countryService.findAllCountries(new SearchPageRequest()))
				.thenReturn(SearchPageResponse.of(countries.size(), 1, 0, countries));

		this.mockMvc.perform(MockMvcRequestBuilders.get(PathConstants.COUNTRIES)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalItems", Matchers.is(5)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.totalPages", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.currentPage", Matchers.is(0)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents.length()", Matchers.is(5)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].name", Matchers.is("Aruba")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].region", Matchers.is("Caribbean")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].continent", Matchers.is("North America")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].area", Matchers.is(193.00)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].alpha2Code", Matchers.is("AW")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.contents[0].alpha3Code", Matchers.is("ABW")));
	}

	@Test
	void shouldReturnCountryWithId() throws Exception {
		final Long countryToLookFor = Long.valueOf(4);
		List<CountryResponse> countries = TestDataBuilder.buildCountries();
		Mockito.when(countryService.findById(countryToLookFor))
				.thenReturn(countries.stream()
									 .filter(countryDTO -> countryDTO.getId().equals(countryToLookFor))
									 .findFirst()
									 .orElseThrow(() -> new EntityNotFoundException("No country found.")));

		this.mockMvc.perform(MockMvcRequestBuilders.get(PathConstants.COUNTRY_BY_ID, countryToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Antigua and Barbuda")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.region", Matchers.is("Caribbean")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.continent", Matchers.is("North America")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nationalDay", Matchers.is("1981-11-01")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.area", Matchers.is(442.00)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.alpha2Code", Matchers.is("AG")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.alpha3Code", Matchers.is("ATG")));
	}

	@Test
	void shouldReturnErrorWhenCountryIsNotPresent() throws Exception {
		final Long countryToLookFor = Long.valueOf(200);
		Mockito.when(countryService.findById(countryToLookFor))
				.thenThrow(new EntityNotFoundException("No country found."));

		this.mockMvc.perform(MockMvcRequestBuilders.get(PathConstants.COUNTRY_BY_ID, countryToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("NOT_FOUND")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.timestamp", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("No country found.")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(404)));
	}

	@Test
	void shouldReturnAllLanguagesForCountry() throws Exception {
		final Long countryToLookFor = Long.valueOf(1);
		List<CountryLanguageResponse> languages = TestDataBuilder.buildCountryLanguages().stream()
				.sorted(Comparator.comparing(CountryLanguageResponse::getLanguage)).collect(Collectors.toList());
		Mockito.when(countryService.findCountryLanguages(countryToLookFor)).thenReturn(languages);

		this.mockMvc.perform(MockMvcRequestBuilders.get(PathConstants.LANGUAGES_BY_COUNTRY, countryToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(4)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].language", Matchers.is("Dutch")))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].official", Matchers.is(true)));
	}
}
