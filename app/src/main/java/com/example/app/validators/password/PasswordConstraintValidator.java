package com.example.app.validators.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.StringJoiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 127),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1)
                ));

        RuleResult result = validator.validate(new PasswordData(password));
        if(result.isValid()) {
            return true;
        }

        context.disableDefaultConstraintViolation();
//        context.buildConstraintViolationWithTemplate(
//                Joiner.on
//        )

        return false;
    }


}
