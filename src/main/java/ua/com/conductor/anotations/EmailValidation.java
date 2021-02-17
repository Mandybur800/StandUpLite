package ua.com.conductor.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import ua.com.conductor.util.EmailValidator;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidation {
    String message() default "Incorrect email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
