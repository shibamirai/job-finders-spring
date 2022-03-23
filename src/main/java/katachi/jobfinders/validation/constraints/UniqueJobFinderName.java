package katachi.jobfinders.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UniqueJobFinderNameValidator.class})
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueJobFinderName {

	String message() default "その名前は既に使用されています";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	@Documented
	@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	public @interface List {
		UniqueJobFinderName[] value();
	}

}
