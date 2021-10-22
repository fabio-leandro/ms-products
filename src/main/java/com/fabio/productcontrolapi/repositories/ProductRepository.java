package com.fabio.productcontrolapi.repositories;

import com.fabio.productcontrolapi.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    Optional<ProductEntity> findByDescription(String description);
}
