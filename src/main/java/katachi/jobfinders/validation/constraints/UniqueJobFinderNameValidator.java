package katachi.jobfinders.validation.constraints;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.service.JobFinderService;

public class UniqueJobFinderNameValidator implements ConstraintValidator<UniqueJobFinderName, Object> {

	@Autowired
	JobFinderService jobFinderService;

	private String message;

	@Override
	public void initialize(UniqueJobFinderName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object form, ConstraintValidatorContext context) {

		BeanWrapper beanWrapper = new BeanWrapperImpl(form);
		String name = (String)beanWrapper.getPropertyValue("name");
		Integer ignoreId = (Integer)beanWrapper.getPropertyValue("id");

		Optional<JobFinder> jobFinder = jobFinderService.getByName(name);

		if (!jobFinder.isPresent() || jobFinder.get().getId().intValue() == Optional.ofNullable(ignoreId).orElse(0).intValue()) {
			return true;
		}
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message)
			.addPropertyNode("name")
			.addConstraintViolation();
		return false;
	}

}
