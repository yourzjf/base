package org.codingtogether.webset.service.impl;

import org.codingtogether.webset.bean.User;
import org.codingtogether.webset.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
