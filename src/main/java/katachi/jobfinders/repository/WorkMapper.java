package katachi.jobfinders.repository;

import org.apache.ibatis.annotations.Mapper;

import katachi.jobfinders.domain.model.Work;

@Mapper
public interface WorkMapper {
	void insertOrUpdate(Work record);
	void delete(int id);
}
