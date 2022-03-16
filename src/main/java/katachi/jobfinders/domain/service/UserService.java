package katachi.jobfinders.domain.service;

import katachi.jobfinders.domain.model.User;

public interface UserService {
	public User getLoginUser(String email);

}
