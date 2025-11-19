package com.sametkaya_finch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gallerist_car", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "gallerist_id", "car_id" }, name = "uq_gallerist_car") })
/*
 * 1 nolu galeri 3 nolu araci var 1 nolu galeri 4 nolu araci var 1 nolu galeri 3
 * nolu araci daha olmamali bu yuzden unique tanimlandi ikisi beraber ayni
 * galeride ayni aracin kaydi bir kere olmali
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class GalleristCar extends BaseEntity {

	@ManyToOne
	private Gallerist gallerist;

	@ManyToOne
	private Car car;

}
