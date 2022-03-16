package katachi.jobfinders.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import katachi.jobfinders.domain.model.User;
import katachi.jobfinders.domain.service.UserService;
import katachi.jobfinders.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public User getLoginUser(String email) {
		return mapper.findLoginUser(email);
	}

}
