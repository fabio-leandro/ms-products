package com.fabio.productcontrolapi.dtos;

import lombok.Data;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;
    @NotBlank(message="The field cannot be blank.")
    @Size(min = 2, message = "The field was not validated")
    private String description;
    @NotNull(message = "The field cannot be null.")
    @DecimalMin(value = "0.01")
    private BigDecimal price;


}
