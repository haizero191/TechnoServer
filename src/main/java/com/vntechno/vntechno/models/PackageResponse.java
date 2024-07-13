package com.vntechno.vntechno.models;


import org.springframework.http.ResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageResponse {
	private Boolean status;
	private ResponseEntity<Object> data;
	private String message;
}
