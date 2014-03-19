package com.kimi.forum.dao;

import java.util.List;

import com.kimi.forum.domain.User;

public class UserDao extends BaseDao<User>{
	private final String GET_USER_BY_USERNAME = "from User u where u.userName = ?";
	
	private final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";
	
	public User getUserByUserName(String userName){
		List<User> users = find(GET_USER_BY_USERNAME, userName);
		if (users.size() == 0) {
			return null;
		}else{
			return users.get(0);
		} 
	}
	public List<User> queryUserByUserName(String userName){
	    return find(QUERY_USER_BY_USERNAME,userName+"%");
	}
}
