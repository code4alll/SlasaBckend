package com.ecommerce.slasa.Validation;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.ecommerce.slasa.Validator.ProductValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProductValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ProductValidValues {
	
	String message() default "Date Sequence Should be Valid!!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
	

}
