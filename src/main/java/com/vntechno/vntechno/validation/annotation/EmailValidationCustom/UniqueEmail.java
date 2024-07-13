package com.vntechno.vntechno.validation.annotation.EmailValidationCustom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Đánh dấu lớp UniqueEmail là một annotation ràng buộc (constraint).
 * Annotation này sẽ được xác thực bởi UniqueEmailValidator.
 */
@Constraint(validatedBy = UniqueEmailValidator.class)
// Định nghĩa các nơi mà annotation này có thể được áp dụng (FIELD, METHOD, PARAMETER)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
// Định nghĩa thời gian tồn tại của annotation này là ở runtime.
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    // Thông báo mặc định khi validation thất bại
    String message() default "Email is already taken";
    // Nhóm mà annotation này thuộc về. Thường sử dụng trong các trường hợp phức tạp hơn.
    Class<?>[] groups() default {};
    // Payload có thể được sử dụng để cung cấp thông tin bổ sung về validation thất bại (ít được sử dụng).
    Class<? extends Payload>[] payload() default {};
}
