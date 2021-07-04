package com.caterpillar.toyrobotapi;

import com.caterpillar.toyrobotapi.controller.ToyRobotController;
import com.caterpillar.toyrobotapi.dto.ToyRobotRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ToyRobotApiApplication.class)
public class ToyRobotApiApplicationTests {

    @Autowired
    private ToyRobotController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void shouldIgnoreReportingOnlyForPlaceCommand() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE 0,0,NORTH")))
				.andExpect(status().isCreated())
				.andExpect(content().string(""));
    }

	@Test
	void shouldReturnToyPositionOnlyForPlaceWithReportCommand() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE   0,0,  NORTH REPORT")))
				.andExpect(status().isCreated())
				.andExpect(content().string("0,0,NORTH"));
	}

	@Test
	void shouldReturnToyPositionForPlaceWithDirectionReport() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE 0,0,NORTH LEFT REPORT")))
				.andExpect(status().isCreated())
				.andExpect(content().string("0,0,WEST"));
	}

	@Test
	void shouldReturnToyPositionOnlyForPlaceWithMultiMoveDirectionAndReport() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE 1,2,EAST MOVE MOVE LEFT MOVE REPORT")))
				.andExpect(status().isCreated())
				.andExpect(content().string("3,3,NORTH"));
	}

	@Test
	void shouldReturnToyPositionOnlyForPlaceWithMultiMoveDirectionsAndReport() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE 0,2,WEST MOVE LEFT RIGHT MOVE LEFT MOVE REPORT")))
				.andExpect(status().isCreated())
				.andExpect(content().string("0,1,SOUTH"));
	}

	@Test
	void shouldReturnToyPositionOnlyForPlaceMultipleDirectionsAndReport() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE 2,1,SOUTH MOVE RIGHT RIGHT MOVE LEFT MOVE LEFT LEFT REPORT")))
				.andExpect(status().isCreated())
				.andExpect(content().string("1,1,EAST"));
	}

	@Test
	void shouldReturnEmptyResponseForWithoutReportCommand() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE 2,1,SOUTH MOVE MOVE LEFT MOVE LEFT LEFT")))
				.andExpect(status().isCreated())
				.andExpect(content().string(""));
	}

	@Test
	void shouldReturnMissingRobotWithoutPosition() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("MOVE REPORT")))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("ROBOT MISSING"));
	}

	@Test
	void shouldReturnMissingRobotWithMultiDirectionsWithoutPosition() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("MOVE LEFT RIGHT LEFT RIGHT REPORT")))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("ROBOT MISSING"));
	}

	@Test
	void shouldReturnInvalidCommandIfUnrecognizedCommand() throws Exception {
		this.mockMvc.perform(post("/v1/command")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.content(buildRequestBody("PLACE 3,2,EAST ABC MOVE LEFT RIGHT LEFT RIGHT REPORT")))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("INVALID COMMAND"));
	}

	private String buildRequestBody(String command) throws JsonProcessingException {
		ToyRobotRequest toyRobotRequest = new ToyRobotRequest();
		toyRobotRequest.setCommand(command);
		ObjectWriter objectWriter = new ObjectMapper().writer();
		return objectWriter.writeValueAsString(toyRobotRequest);
	}

}
