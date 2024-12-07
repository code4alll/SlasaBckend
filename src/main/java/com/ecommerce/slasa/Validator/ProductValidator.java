package com.ecommerce.slasa.Validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ecommerce.slasa.Model.Product;
import com.ecommerce.slasa.Validation.ProductValidValues;

public class ProductValidator implements ConstraintValidator<ProductValidValues, Product> {

	@Override
	public boolean isValid(Product value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return true;
	}

}
