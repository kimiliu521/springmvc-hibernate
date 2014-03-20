package com.kimi.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kimi.forum.dao.UserDao;
import com.kimi.forum.domain.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void register(User user) throws Exception {
		User u = this.getUserByName(user.getUserName());
		if(u != null) {
			throw new Exception();
		}else {
			user.setCredit(100);
			user.setUserType(1);
			userDao.save(user);
		}
	}

	private User getUserByName(String userName) {
		return userDao.getUserByUserName(userName);
	}
}
