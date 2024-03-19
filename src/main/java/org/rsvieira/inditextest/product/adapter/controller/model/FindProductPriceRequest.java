package org.rsvieira.inditextest.product.adapter.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Getter
@AllArgsConstructor
public class FindProductPriceRequest {

  @NotNull(message = "Brand id is required")
  @Min(value = 1, message = "Brand id must be greater than 0")
  @Schema(description = "Brand identifier", example = "1", requiredMode = REQUIRED)
  private Long brandId;

  @NotNull(message = "Product id is required")
  @Min(value = 1, message = "Product id must be greater than 0")
  @Schema(description = "Product code", example = "35455", requiredMode = REQUIRED)
  private Long productId;

  @NotNull(message = "Date is required")
  @DateTimeFormat(iso = DATE_TIME)
  @Schema(description = "Date", example = "2020-06-14T16:00:00", requiredMode = REQUIRED)
  private LocalDateTime date;

}
