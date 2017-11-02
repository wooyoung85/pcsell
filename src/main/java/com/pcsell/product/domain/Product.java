package com.pcsell.product.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Product {	
	@Id
	@GeneratedValue
	private Long id;		
	private String productName;
	private Long productPrice;
	@Lob
	private String productDesc;
	private int productScore;	
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_product_category"))
	private ProductCategory productCategory;
	
	public Product(){
		
	}

	public Product(Long id, String productName, Long productPrice, String productDesc, int productScore) {
		super();
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDesc = productDesc;
		this.productScore = productScore;
	}	
}
