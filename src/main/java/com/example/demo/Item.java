package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="item")
public class Item {
	
	@Id
	@Column(name="code")
	private Integer code;

	@Column(name="category_code")
	private Integer categoryCode;
	
	@Column(name="name")
	private String name;
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="price")
	private Integer price;
	
	@Column(name="sell_count")
	private Integer sellCount;
	
	@Transient
	private Integer quantity;
	
	public Integer getCode() {
		return code;
	}
	
	public Integer getCategoryCode() {
		return categoryCode;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDetail() {
		return detail;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer getSellCount() {
		return sellCount;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
