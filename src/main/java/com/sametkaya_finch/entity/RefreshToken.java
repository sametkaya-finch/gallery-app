package com.sametkaya_finch.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends BaseEntity {

	@Column(name = "refreshed_token")
	private String refreshToken;

	@Column(name = "expired_date")
	private Date expiredDate;

	@ManyToOne
	private User user;

}
