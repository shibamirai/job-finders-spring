package katachi.jobfinders.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobFinder {
	private Integer id;
	private String avatar;
	private String name;
	private int gender;
	private int age;
	private int handicap;
	private boolean hasCertificate;
	private LocalDate useFrom;
	private String skills;
	private String occupation;
	private String description;
	private LocalDate hiredAt;
	private int employmentPattern;
	private boolean isHandicapsOpened;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private int worksCount;
	private List<Work> works;

	public JobFinder(
		Integer id,
		String avatar,
		String name,
		Gender gender,
		int age,
		Handicap handicap,
		boolean hasCertificate,
		LocalDate useFrom,
		String skills,
		String occupation,
		String description,
		LocalDate hiredAt,
		EmploymentPattern employmentPattern,
		boolean isHandicapsOpened
	) {
		this.id = id;
		this.avatar = avatar;
		this.name = name;
		this.gender = gender.getCode();
		this.age = age;
		this.handicap = handicap.getCode();
		this.hasCertificate = hasCertificate;
		this.useFrom = useFrom;
		this.skills = skills;
		this.occupation = occupation;
		this.description = description;
		this.hiredAt = hiredAt;
		this.employmentPattern = employmentPattern.getCode();
		this.isHandicapsOpened = isHandicapsOpened;
	}

	public Gender gender() {
		return Gender.from(gender);
	}

	public Handicap handicap() {
		return Handicap.from(handicap);
	}

	public EmploymentPattern employmentPattern() {
		return EmploymentPattern.from(employmentPattern);
	}

	public String getPeriodOfUse() {
		Period period = Period.between(useFrom, hiredAt);

		String periodOfUse = period.get(ChronoUnit.MONTHS) + "ヶ月";
		if (period.get(ChronoUnit.YEARS) > 0) {
			periodOfUse = period.get(ChronoUnit.YEARS) + "年" + periodOfUse;
		}
		return periodOfUse;
	}
}
