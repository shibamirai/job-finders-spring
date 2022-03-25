package katachi.jobfinders.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import katachi.jobfinders.domain.model.Work;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkForm {
	private Integer id;

	@NotBlank
	private String content;

	private String title;

	@URL
	private String url;

	@NotBlank
	private String languages;

	@Min(0)
	private Integer creationTime;

	private String description;

	public WorkForm(Work work) {
		this.id = work.getId();
		this.content = work.getContent();
		this.title = work.getTitle();
		this.url = work.getUrl();
		this.languages = work.getLanguages();
		this.creationTime = work.getCreationTime();
		this.description = work.getDescription();
	}

	public Work toWork(int jobFinderId) {
		return new Work(id, Integer.valueOf(jobFinderId), content,
			title, url, languages, creationTime, description
		);
	}
}
