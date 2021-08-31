package com.example.humanresources.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.*;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	@Email
	@NotBlank
	@NotNull
	private String email;

	@JsonIgnore
	@Column(name="password")
	@NotBlank
	@NotNull
	@Size(min = 6)
	private String password;

//	@Column(name="name")
//	@NotNull
//	@Size(min = 2, max = 15)
//	private String name;
//
//	@Column(name="LastName")
//	@NotNull
//	@Size(min = 2, max = 15)
//	private String LastName;

	@Column(name = "mail_is_verify")
	private boolean mailVerify;
	@Column(name = "user_type")
	private int userType;

}
