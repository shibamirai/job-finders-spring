package katachi.jobfinders.domain.model;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Work {
	private Integer id;
	private Integer jobFinderId;
	private String content;
	private String title;
	private String url;
	private String languages;
	private Integer creationTime;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Work(
		Integer id,
		Integer jobFinderId,
		String content,
		String title,
		String url,
		String languages,
		Integer creationTime,
		String description
	) {
		this.id = id;
		this.jobFinderId = jobFinderId;
		this.content = content;
		this.title = title;
		this.url = url;
		this.languages = languages;
		this.creationTime = creationTime;
		this.description = description;
	}

	public String getPeriodOfCreation() {
		String periodOfCreation = "制作期間不明";

		Optional<Integer> creationTime = Optional.ofNullable(this.creationTime);
		if (creationTime.isPresent()) {
			int year = creationTime.get() / 12;
			int month = creationTime.get() % 12;
			periodOfCreation = (year > 0 ? (year + "年") : "") + month + "ヶ月";
		}

		return periodOfCreation;
	}

}
