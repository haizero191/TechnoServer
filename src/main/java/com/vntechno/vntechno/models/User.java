package com.vntechno.vntechno.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vntechno.vntechno.validation.annotation.EmailValidationCustom.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;





@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
	@Id
    private String id; 

	@Indexed(unique = true)
	@NonFinal
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	@UniqueEmail // Kiểm tra email duy nhất - custom validator
	private String email;


	@NotBlank(message = "Password is mandatory")
	private String password;

	@NotBlank(message = "Your phone is mandatory")
	private String phone;

	@NotBlank(message = "Your fullname is mandatory")
	private String fullname;
}
