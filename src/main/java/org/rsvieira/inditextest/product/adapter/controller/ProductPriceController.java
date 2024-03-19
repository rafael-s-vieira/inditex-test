package org.rsvieira.inditextest.product.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.rsvieira.inditextest.product.adapter.controller.exception.GlobalErrorResponse;
import org.rsvieira.inditextest.product.adapter.controller.mapper.ProductPriceResponseMapper;
import org.rsvieira.inditextest.product.adapter.controller.model.FindProductPriceRequest;
import org.rsvieira.inditextest.product.adapter.controller.model.FindProductPriceResponse;
import org.rsvieira.inditextest.product.domain.ports.service.ProductPriceService;
import org.rsvieira.inditextest.product.domain.ports.service.exception.InvalidParametersException;
import org.rsvieira.inditextest.product.domain.ports.service.exception.PriceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@Log4j2
@RequiredArgsConstructor
public class ProductPriceController {

  private final ProductPriceService productPriceService;

  private final ProductPriceResponseMapper mapper;

  @GetMapping(value = "/v1/product-price", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Queries the price of a product in a given date, for a given brand")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Price found for the given brand, product and date"),
          @ApiResponse(
              responseCode = "400",
              content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalErrorResponse.class)),
              description = "Invalid request parameters. Brand id, product id and date are required and must be valid"),
          @ApiResponse(
              responseCode = "404",
              content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalErrorResponse.class)),
              description = "No price found for the given brand, product and date."),
          @ApiResponse(
              responseCode = "500",
              content = @Content(mediaType = "application/json", schema = @Schema(implementation = GlobalErrorResponse.class)),
              description = "Generic error. An error occurred while processing the request."),
      })
  public ResponseEntity<FindProductPriceResponse> findProductPrice(@Valid FindProductPriceRequest request) throws PriceNotFoundException, InvalidParametersException {
    LOGGER.info("Controller finding product price for brandId: {}, productId: {}, date: {}", request.getBrandId(), request.getProductId(), request.getDate());

    var productPrice = productPriceService.findProductPrice(request.getBrandId(), request.getProductId(), request.getDate());
    return ResponseEntity.ok(mapper.toResponse(productPrice));
  }

}
