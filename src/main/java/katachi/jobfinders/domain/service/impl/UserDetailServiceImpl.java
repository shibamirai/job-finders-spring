package katachi.jobfinders.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import katachi.jobfinders.domain.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// ユーザー情報取得
		UserDetails userDetails = service.getLoginUser(email);

		// ユーザーが存在しなければ例外を投げる
		if (userDetails == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return userDetails;
	}

}
