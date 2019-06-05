package com.virtusa.inventory.modal;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class LoyaltyCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull(message = "Name can not be empty")
	@Size(min = 3)
	private String name;

	@NotNull(message = "Number can not be empty")
	@Size(min = 8, max = 8)
	@Column(unique = true)
	private String number;

	private Double pointBalance;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date issuedDate;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date expiryDate;

	@OneToOne(mappedBy = "card")
	@JsonIgnore
	private Customer cutomer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", referencedColumnName = "id")

	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Double getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(Double pointBalance) {
		this.pointBalance = pointBalance;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Customer getCutomer() {
		return cutomer;
	}

	public void setCutomer(Customer cutomer) {
		this.cutomer = cutomer;
	}

}
