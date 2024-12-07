package com.ecommerce.slasa.Validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ecommerce.slasa.Validator.UserValidator;

@Constraint(validatedBy = UserValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public  @interface UserValidValues {
	
	String message() default "Date Sequence Should be Valid!!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	

}