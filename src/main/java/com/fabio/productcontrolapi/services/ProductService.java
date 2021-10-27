package com.fabio.productcontrolapi.services;

import com.fabio.productcontrolapi.dtos.ProductDTO;
import com.fabio.productcontrolapi.entities.ProductEntity;
import com.fabio.productcontrolapi.exceptions.ProductNotFoundException;
import com.fabio.productcontrolapi.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    ModelMapper modelMapper;


    public ProductDTO save(ProductDTO productDTO){
        ProductEntity productEntity = productRepository.save(modelMapper.map(productDTO,ProductEntity.class));
        return modelMapper.map(productEntity,ProductDTO.class);
    }

    public ProductDTO findByDescription(String description) throws ProductNotFoundException{
        ProductEntity productEntity = productRepository.findByDescription(description)
                .orElseThrow(()-> new ProductNotFoundException("The product was not found."));
        return modelMapper.map(productEntity,ProductDTO.class);
    }


    public void deleteByDescription(String description) throws ProductNotFoundException {
        ProductEntity productEntity = modelMapper.map(findByDescription(description),ProductEntity.class);
        productRepository.deleteById(productEntity.getId());
    }


    public ProductDTO updateProductByDescription(String description, ProductDTO productDTO)
            throws ProductNotFoundException {
        findByDescription(description);
        ProductEntity productEntity = modelMapper.map(productDTO,ProductEntity.class);
        productRepository.save(productEntity);
        return modelMapper.map(productEntity,ProductDTO.class);
    }
}
