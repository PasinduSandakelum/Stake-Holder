package com.virtusa.inventory.modal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Telephone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Phone number can't be empty")
	@Size(min = 10, max = 10)
	private String number;

	@NotNull(message = "Type can't be empty")
	private String type;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	Customer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
