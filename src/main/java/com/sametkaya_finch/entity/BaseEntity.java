package com.sametkaya_finch.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
//tablo olmayacak bunu extends eden tum tablolar sutun olarak alacak demek
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_time")
	@DateTimeFormat(iso = ISO.DATE_TIME) // gun ay yil saat dakika saniye cinsinden
	private Date createTimeDate;

}
