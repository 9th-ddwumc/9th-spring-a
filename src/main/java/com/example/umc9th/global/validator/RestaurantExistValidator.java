package com.example.umc9th.global.validator;

import com.example.umc9th.domain.restraunt.exception.code.RestaurantErrorCode;
import com.example.umc9th.domain.restraunt.repository.RestaurantRepository;
import com.example.umc9th.global.annotation.ExistRestaurants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantExistValidator implements ConstraintValidator<ExistRestaurants, Long> {

    private final RestaurantRepository restaurantRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        boolean isValid = restaurantRepository.existsById(value);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(RestaurantErrorCode.NOT_FOUND.getMessage()).addConstraintViolation();
        }

        return isValid;
    }
}
