package com.ecom.ordersservice.repsoitories;


import com.ecom.ordersservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
