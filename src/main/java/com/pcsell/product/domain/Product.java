package com.pcsell.product.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 20, unique=true)
	private String productId;
	private String productName;
	private Long productPrice;
	//PC 스펙값 추가 필요
	
	public Product(){
		
	}
	
	public Product(String productID, String productName, Long productPrice){
		super();
		this.productId = productID;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	

}
