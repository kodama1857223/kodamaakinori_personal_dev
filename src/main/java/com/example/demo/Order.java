package com.example.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ordered")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="code")
	private Integer code;

	@Column(name="customer_code")
	private Integer customerCode;
	
	@Column(name="ordered_date")
	private Date orderedDate;

	@Column(name="total_price")
	private Integer totalPrice;

	
	public Order(Integer code, Integer customerCode, Date orderedDate, Integer totalPrice) {
		this(customerCode, orderedDate, totalPrice);
		this.code = code;
	}
	
	public Order(Integer customerCode, Date orderedDate, Integer totalPrice) {
		super();
		this.customerCode = customerCode;
		this.orderedDate = orderedDate;
		this.totalPrice = totalPrice;
	}
	
	public Order() {
		super();
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public Integer getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(Integer customerCode) {
		this.customerCode = customerCode;
	}
	
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}
	
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
