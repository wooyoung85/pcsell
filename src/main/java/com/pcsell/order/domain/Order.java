package com.pcsell.order.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pcsell.product.domain.Product;
import com.pcsell.user.domain.User;

import lombok.Data;

@Entity
@Data
public class Order {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_order_orderer"))
	private User orderer;
	
	@OneToMany
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_order_product"))
	private Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date cancelDate;
	
	private int cancel;
	// 0 - 신청, 1 - 취소
	
	public Order(){
		
	}
	
	public Order(User orderer, Product product){
		this.orderer = orderer;
		this.product = product;
		this.orderDate = new Date();
		this.cancel = 0;
	}
	
	public void Cancel(){
		this.cancelDate = new Date();
		this. cancel = 1;
	}
	
	public boolean isOrderer(User loginUser) {
		return this.orderer.equals(loginUser);
	}
}
