package com.growtransparency.settings.validations.validator;

import com.growtransparency.repositories.UserRepository;
import com.growtransparency.settings.validations.notations.EmailEquals;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class EqualsEmailValidator implements ConstraintValidator<EmailEquals, String> {
    private final UserRepository userRepository;

    public EqualsEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        var optionalUser = userRepository.findByEmail(value);
        return !optionalUser.isPresent();
    }
}
