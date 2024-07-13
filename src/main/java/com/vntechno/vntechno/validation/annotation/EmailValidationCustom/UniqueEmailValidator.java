package com.vntechno.vntechno.validation.annotation.EmailValidationCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vntechno.vntechno.repository.UserRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepo userRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            // Nếu email là null hoặc rỗng, validation sẽ được xử lý bởi các annotation khác như @NotBlank
            return true; 
        }
         // Kiểm tra xem email đã tồn tại chưa
        return !userRepository.existsByEmail(email);
    }
}
