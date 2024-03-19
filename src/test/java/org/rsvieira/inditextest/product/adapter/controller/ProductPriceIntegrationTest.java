package org.rsvieira.inditextest.product.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.rsvieira.inditextest.product.adapter.controller.model.FindProductPriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.rsvieira.inditextest.product.Fixtures.EXPECTED_RESPONSE_1;
import static org.rsvieira.inditextest.product.Fixtures.EXPECTED_RESPONSE_2;
import static org.rsvieira.inditextest.product.Fixtures.EXPECTED_RESPONSE_3;
import static org.rsvieira.inditextest.product.Fixtures.EXPECTED_RESPONSE_4;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "classpath:data/test-data.sql")
public class ProductPriceIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @ParameterizedTest(name = "{index}: {4}")
  @MethodSource("validTestCases")
  @SneakyThrows
  void givenValidInputWhenInvokedShouldReturnCorrectOutput(String brandId, String productId, String date, FindProductPriceResponse expectedResponse, String scenario) {
    mockMvc.perform(
            get("/api/v1/product-price").accept(APPLICATION_JSON)
                .param("brandId", brandId)
                .param("productId", productId)
                .param("date", date))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(expectedResponse)));
  }

  @ParameterizedTest(name = "{index}: {3}")
  @MethodSource("invalidTestCases")
  @SneakyThrows
  void givenInvalidInputWhenInvokedShouldReturn400Error(String brandId, String productId, String date, String scenario) {
    mockMvc.perform(
            get("/api/v1/product-price").accept(APPLICATION_JSON)
                .param("brandId", brandId)
                .param("productId", productId)
                .param("date", date))
        .andExpect(status().isBadRequest());
  }

  @Test
  @SneakyThrows
  void givenOutOfLimitsInputWhenInvokedShouldReturn404Error() {
    mockMvc.perform(
            get("/api/v1/product-price").accept(APPLICATION_JSON)
                .param("brandId", "1")
                .param("productId", "35455")
                .param("date", "2024-06-14T10:00:00"))
        .andExpect(status().isNotFound());
  }

  private static Stream<Arguments> validTestCases() {
    return Stream.of(
        Arguments.of("1", "35455", "2020-06-14T10:00:00", EXPECTED_RESPONSE_1, "Test case 1"),
        Arguments.of("1", "35455", "2020-06-14T16:00:00", EXPECTED_RESPONSE_2, "Test case 2"),
        Arguments.of("1", "35455", "2020-06-14T21:00:00", EXPECTED_RESPONSE_1, "Test case 3"),
        Arguments.of("1", "35455", "2020-06-15T10:00:00", EXPECTED_RESPONSE_3, "Test case 4"),
        Arguments.of("1", "35455", "2020-06-16T21:00:00", EXPECTED_RESPONSE_4, "Test case 5")
    );
  }

  private static Stream<Arguments> invalidTestCases() {
    return Stream.of(
        Arguments.of(null, "35455", "2020-06-14T10:00:00",  "Null brandId"),
        Arguments.of("0", "35455", "2020-06-14T16:00:00",  "Invalid brandId"),
        Arguments.of("1", null, "2020-06-14T21:00:00",  "Null productId"),
        Arguments.of("1", "0", "2020-06-15T10:00:00",  "Invalid productId"),
        Arguments.of("1", "35455", null,  "Null date"),
        Arguments.of(null, null, null,  "Null all params")
    );
  }
}
