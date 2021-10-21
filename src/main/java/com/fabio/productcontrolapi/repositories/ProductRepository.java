package com.fabio.productcontrolapi.repositories;

import com.fabio.productcontrolapi.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
