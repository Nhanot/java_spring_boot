package com.nha.miniProject.model;

import com.nha.miniProject.constants.OrderConstants;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Order" , schema = OrderConstants.SCEMAS)
public class Order {

}
