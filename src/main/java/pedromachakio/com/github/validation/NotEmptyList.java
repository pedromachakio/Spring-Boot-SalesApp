package pedromachakio.com.github.validation;

import pedromachakio.com.github.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // para ser verificada em tempo de execução
@Target(ElementType.FIELD) // para dizer onde podemos meter esta anotação
@Constraint(validatedBy = NotEmptyListValidator.class) // é o que diz que esta é uma annotation de validação e temos que passar qual a class que vai validar
public @interface NotEmptyList {

    String message() default "List can't be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {}; // copied from NotNull.class and is apparently mandatory to implement
}
