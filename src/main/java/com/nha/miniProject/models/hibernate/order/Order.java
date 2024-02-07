package com.nha.miniProject.models.hibernate.order;

import com.nha.miniProject.constants.OrderConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Order" , schema = OrderConstants.SCEMAS)
public class Order {

    @Id
    @Column(name = "id")
    private String id;
}
