package com.customer.service.customer.aggregate.controller;

import com.customer.service.customer.aggregate.domain.dto.CustomerDto;
import com.customer.service.customer.aggregate.doubles.factories.CustomerStubCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FlywayTest
@Transactional
@TestExecutionListeners(
        listeners = {
                DependencyInjectionTestExecutionListener.class,
                FlywayTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class CustomerControllerITest {
    private static final String API_PREFIX = "/api/v1/customers";

    private static ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void setUpClass() {
        objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    public void create_whenCorrectParams_returnsCustomerDtoAndStatusCreated() throws Exception {
        CustomerDto expected = CustomerStubCreator.createCustomerDto();

        String content = objectMapper.writeValueAsString(expected);

        String responseContent = mockMvc
                .perform(post(API_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        CustomerDto actual = objectMapper.readValue(responseContent, CustomerDto.class);

        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expected);
    }

    @Test
    public void create_whenInvalidParams_returnsErrorMessageAndStatusBadRequest() throws Exception {
        CustomerDto expected = CustomerStubCreator.createCustomerDtoWithMissingName();

        String content = objectMapper.writeValueAsString(expected);

        mockMvc
                .perform(post(API_PREFIX)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Name cannot be null"));
    }

    @Test
    @Sql("/customers.sql")
    public void getAll_whenCustomersExist_returnsAllAndStatusOk() throws Exception {
        List<CustomerDto> expected = CustomerStubCreator.createCustomerDtos();

        String responseContent = mockMvc
                .perform(get(API_PREFIX))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<CustomerDto> response = objectMapper.readValue(responseContent, objectMapper.getTypeFactory().constructCollectionType(List.class, CustomerDto.class));

        assertThat(response).hasSameElementsAs(expected);
    }

    @Test
    public void getAll_whenCustomersDoesntExist_returnsEmptyListAndStatusNotFound() throws Exception {
        String responseContent = mockMvc
                .perform(get(API_PREFIX))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<CustomerDto> response = objectMapper.readValue(responseContent, objectMapper.getTypeFactory().constructCollectionType(List.class, CustomerDto.class));

        assertThat(response).isEmpty();
    }
}
