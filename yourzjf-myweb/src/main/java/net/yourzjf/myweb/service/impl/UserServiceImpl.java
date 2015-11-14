package net.yourzjf.myweb.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.yourzjf.myweb.bean.User;
import net.yourzjf.myweb.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
