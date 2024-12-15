package com.ecom.ordersservice.repsoitories;


import com.ecom.ordersservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
