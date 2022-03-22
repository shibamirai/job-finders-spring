package katachi.jobfinders.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.model.JobFinderSearchParam;

@Mapper
public interface JobFinderMapper {
	public Integer getCount();
	public List<JobFinder> selectAll();
	public Integer getCount(JobFinderSearchParam param);
	public List<JobFinder> pagenate(JobFinderSearchParam param);
	public JobFinder selectById(int id);
	public JobFinder selectByName(String name);
	void insertOrUpdate(JobFinder record);
}
