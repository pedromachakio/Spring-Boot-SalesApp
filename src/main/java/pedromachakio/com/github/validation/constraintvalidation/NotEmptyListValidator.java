package pedromachakio.com.github.validation.constraintvalidation;

import pedromachakio.com.github.validation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> { // 1 arg - annotation, 2 arg - tipo de dado que tem que validar

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {

        return list != null && !list.isEmpty();
    }
}
