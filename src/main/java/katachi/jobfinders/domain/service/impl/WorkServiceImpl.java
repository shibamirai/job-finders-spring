package katachi.jobfinders.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.jobfinders.domain.model.Work;
import katachi.jobfinders.domain.service.WorkService;
import katachi.jobfinders.repository.WorkMapper;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkMapper mapper;

	@Override
	public void register(Work work) {
		mapper.insertOrUpdate(work);
	}

	@Override
	public void delete(int id) {
		mapper.delete(id);
	}

}
