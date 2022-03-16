package katachi.jobfinders.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.jobfinders.domain.model.JobFinder;
import katachi.jobfinders.domain.model.JobFinderSearchParam;
import katachi.jobfinders.domain.service.JobFinderService;
import katachi.jobfinders.repository.JobFinderMapper;

@Service
public class JobFinderServiceImpl implements JobFinderService {

	@Autowired
	private JobFinderMapper mapper;

	@Override
	public int getCount() {
		return mapper.getCount();
	}

	@Override
	public List<JobFinder> getAll() {
		return mapper.selectAll();
	}

	@Override
	public int getCount(JobFinderSearchParam param) {
		return mapper.getCount(param);
	}

	@Override
	public List<JobFinder> pagenate(JobFinderSearchParam param) {
		return mapper.pagenate(param);
	}

	@Override
	public JobFinder getById(int id) {
		return mapper.selectById(id);
	}

}
