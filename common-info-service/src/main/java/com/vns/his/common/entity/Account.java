package com.vns.his.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
	@Id
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "last_login")
	private Date lastLogin;
}
