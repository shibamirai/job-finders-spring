package katachi.jobfinders.domain.model;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Work {
	private int id;
	private int jobFinderId;
	private String content;
	private String title;
	private String url;
	private String languages;
	private Integer creationTime;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Optional<Integer> getCreationTime() {
		return Optional.ofNullable(creationTime);
	}

	public String getPeriodOfCreation() {
		String periodOfCreation = "制作期間不明";

		Optional<Integer> creationTime = getCreationTime();
		if (creationTime.isPresent()) {
			int year = creationTime.get() / 12;
			int month = creationTime.get() % 12;
			periodOfCreation = (year > 0 ? (year + "年") : "") + month + "ヶ月";
		}

		return periodOfCreation;
	}

}
