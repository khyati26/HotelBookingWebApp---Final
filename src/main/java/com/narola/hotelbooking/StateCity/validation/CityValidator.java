package com.narola.hotelbooking.StateCity.validation;

import com.narola.hotelbooking.StateCity.model.City;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class CityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return City.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        City city = (City) target;
        if (city.getStateId() == 0) {
            errors.rejectValue("stateId", "City.stateid.empty");
        }
        if (!Pattern.matches("[a-zA-Z\\s]+", city.getCityName())) {
            errors.rejectValue("cityName", "City.name.invalid");
        }
        if (city.getCityimage().isEmpty()) {
            errors.rejectValue("cityimage", "City.image.empty");
        }
    }
}
