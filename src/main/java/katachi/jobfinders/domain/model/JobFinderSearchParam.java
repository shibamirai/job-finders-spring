package katachi.jobfinders.domain.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class JobFinderSearchParam {
	private final int limit = 9;
	private int page = 1;
	private String search;

	public int getOffset() {
		if (page < 1) {
			log.warn("Set offset = 0 because of invalide page number: " + page);
			return 0;
		};
		return limit * (page - 1);
	}
}
