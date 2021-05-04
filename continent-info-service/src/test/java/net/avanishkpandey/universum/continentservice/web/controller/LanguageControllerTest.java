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
import net.avanishkpandey.universum.continentservice.domain.dto.LanguageResponse;
import net.avanishkpandey.universum.continentservice.domain.dto.SearchPageRequest;
import net.avanishkpandey.universum.continentservice.service.LanguageService;

@WebMvcTest(LanguageController.class)
class LanguageControllerTest {
	private static final String LANGUAGES_API_PATH = "/languages";
	private static final String LANGUAGE_BY_ID_API_PATH = LANGUAGES_API_PATH + "/{languageId}";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LanguageService languageService;

	public LanguageControllerTest() {
	}

	@Test
	void shouldReturnAllLanguages() throws Exception {
		List<LanguageResponse> languages = TestDataBuilder.buildLanguages();
		Mockito.when(languageService.findAllLanguages(new SearchPageRequest())).thenReturn(languages);

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(LANGUAGES_API_PATH)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(20)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].language", Matchers.is("Dutch")));
	}

	@Test
	void shouldReturnLanguageWithId() throws Exception {
		final Long languageToLookFor = 8L;
		Mockito.when(languageService.findLanguageByID(languageToLookFor))
				.thenReturn(TestDataBuilder.buildLanguages()
						.stream()
						.filter(languageDTO -> languageDTO.getId().equals(languageToLookFor))
						.findFirst()
						.orElseThrow(() -> new EntityNotFoundException("No language found.")));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(LANGUAGE_BY_ID_API_PATH, languageToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(languageToLookFor.intValue())))
				.andExpect(MockMvcResultMatchers.jsonPath("$.language", Matchers.is("Turkmenian")));
	}

	@Test
	void shouldReturnErrorWhenLanguageIsNotPresent() throws Exception {
		final Long languageToLookFor = 200L;
		Mockito.when(languageService.findLanguageByID(languageToLookFor))
				.thenThrow(new EntityNotFoundException("No language found."));

		this.mockMvc
				.perform(MockMvcRequestBuilders.get(LANGUAGE_BY_ID_API_PATH, languageToLookFor)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("NOT_FOUND")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.timestamp", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("No language found.")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Matchers.is(404)));
	}
}
