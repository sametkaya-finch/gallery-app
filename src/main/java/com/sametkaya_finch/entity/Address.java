package com.sametkaya_finch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity {

	@Column(name = "city")
	private String cityString;

	@Column(name = "district")
	private String districtString;

	@Column(name = "neighborhood")
	private String neighborhood;

	@Column(name = "street")
	private String street;

}
