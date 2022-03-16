package katachi.jobfinders.domain.service;

import java.util.List;

import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.model.JobFinderSearchParam;

public interface JobFinderService {
	public int getCount();
	public List<JobFinder> getAll();
	public int getCount(JobFinderSearchParam param);
	public List<JobFinder> pagenate(JobFinderSearchParam param);
	public JobFinder getById(int id);
}
