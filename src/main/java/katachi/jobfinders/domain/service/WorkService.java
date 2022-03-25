package katachi.jobfinders.domain.service;

import katachi.jobfinders.domain.model.Work;

public interface WorkService {
	public void register(Work work);
	public void delete(int id);

}
