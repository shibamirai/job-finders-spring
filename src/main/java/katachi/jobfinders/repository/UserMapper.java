package katachi.jobfinders.repository;

import org.apache.ibatis.annotations.Mapper;

import katachi.jobfinders.domain.model.User;

@Mapper
public interface UserMapper {

	/** ログインユーザー取得 */
	public User findLoginUser(String email);
}
