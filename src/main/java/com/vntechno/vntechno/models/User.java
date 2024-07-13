package com.vntechno.vntechno.models;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

	// Email người dùng
	@Indexed(unique = true)
	@NonFinal
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	@UniqueEmail // Kiểm tra email duy nhất - custom validator
	private String email;

	// Mật khẩu
	@NotBlank(message = "Password is mandatory")
	private String password;

	// Số điện thoại
	@NotBlank(message = "Your phone is mandatory")
	private String phone;

	// Tên đầy đủ
	@NotBlank(message = "Your fullname is mandatory")
	private String fullname;

	// Danh sách các vai trò của người dùng
	@DBRef
    private Role role; 

	// Thời gian tạo người dùng
	@CreatedDate
    private Date createdAt;
}
