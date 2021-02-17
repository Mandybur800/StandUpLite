package ua.com.conductor.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ua.com.conductor.anotations.EmailValidation;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private final String EMAIL_REGEX = "\\w+@[a-z]+\\.[a-z]+";
    @Override
    public void initialize(EmailValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
