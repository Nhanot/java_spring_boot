package com.nha.miniProject.repository.order;

import com.nha.miniProject.models.hibernate.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
}
