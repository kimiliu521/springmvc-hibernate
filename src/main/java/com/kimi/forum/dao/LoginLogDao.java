package com.kimi.forum.dao;

import org.springframework.stereotype.Repository;

import com.kimi.forum.domain.LoginLog;

@Repository
public class LoginLogDao extends BaseDao<LoginLog>{
	public void save(LoginLog loginLog) {
		save(loginLog);
	}
}
