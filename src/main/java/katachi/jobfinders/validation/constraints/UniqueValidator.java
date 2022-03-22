package katachi.jobfinders.validation.constraints;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.service.JobFinderService;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

	private int ignoreId;

	@Autowired
	JobFinderService jobFinderService;

	@Override
	public void initialize(Unique constraintAnnotation) {
		ignoreId = constraintAnnotation.ignore();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Optional<JobFinder> jobFinder = jobFinderService.getByName(value);

		return !jobFinder.isPresent() || jobFinder.get().getId() == ignoreId;
	}

}
