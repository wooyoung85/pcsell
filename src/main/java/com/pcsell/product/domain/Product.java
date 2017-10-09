package com.pcsell.product.domain;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Product {
	
	private Long productID;
	private String productName;
	private Long productPrice;

}
