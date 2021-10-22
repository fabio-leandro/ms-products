package com.fabio.productcontrolapi.controllers;

import com.fabio.productcontrolapi.controllers.validations.ProductValidationsErrors;
import com.fabio.productcontrolapi.dtos.ProductDTO;
import com.fabio.productcontrolapi.exceptions.ProductNotFoundException;
import com.fabio.productcontrolapi.services.ProductService;
import com.fasterxml.jackson.core.JsonParseException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody @Valid ProductDTO productDTO){
        ProductDTO productDTOsalved = productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTOsalved);
    }

    @GetMapping("/{description}")
    public ResponseEntity<ProductDTO> findByDescription(@PathVariable String description) throws ProductNotFoundException {
        ProductDTO productDTO = productService.findByDescription(description);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> callConstraintViolationException(){
        return new ProductValidationsErrors().callConstraintViolationException();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handlerValidationException(MethodArgumentNotValidException ex){
        return new ProductValidationsErrors().callValidatorsException(ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonParseException.class)
    public Map<String, Object> callJsonParseException(){
        return new ProductValidationsErrors().callJsonException();
    }


}
