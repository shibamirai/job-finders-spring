package katachi.jobfinders.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import katachi.jobfinders.domain.model.EmploymentPattern;
import katachi.jobfinders.domain.model.Gender;
import katachi.jobfinders.domain.model.Handicap;
import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.validation.constraints.Unique;
import lombok.Data;

@Data
public class JobFinderForm {
	@NotBlank
	private String avatar;

	@NotBlank
	@Unique
	private String name;

	@NotNull
	private Gender gender;

	@NotNull
	@Range(min=18, max=60)
	private int age;

	@NotNull
	private Handicap handicap;

	private boolean hasCertificate = true;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate useFrom;

	private String skills;

	@NotBlank
	private String occupation;

	private String description;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hiredAt;

	@NotNull
	private EmploymentPattern employmentPattern;

	private boolean isHandicapsOpened = true;

	public JobFinderForm() {
	}

	public JobFinderForm(JobFinder jobFinder) {
		this.avatar = jobFinder.getAvatar();
		this.name = jobFinder.getName();
		this.gender = jobFinder.gender();
		this.age = jobFinder.getAge();
		this.handicap = jobFinder.handicap();
		this.useFrom = jobFinder.getUseFrom();
		this.skills = jobFinder.getSkills();
		this.occupation = jobFinder.getOccupation();
		this.description = jobFinder.getDescription();
		this.hiredAt = jobFinder.getHiredAt();
		this.employmentPattern = jobFinder.employmentPattern();
		this.isHandicapsOpened = jobFinder.isHandicapsOpened();
	}

	public JobFinder toJobFinder() {
		return new JobFinder(
			avatar, name, gender, age, handicap, hasCertificate, useFrom, skills, occupation, description, hiredAt,
			employmentPattern, isHandicapsOpened
		);
	}
}
