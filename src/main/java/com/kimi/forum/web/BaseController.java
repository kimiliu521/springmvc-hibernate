package com.kimi.forum.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

import com.kimi.forum.cons.CommonConstant;
import com.kimi.forum.domain.User;

public class BaseController {
	protected final String ERROR_MSG_KEY = "errorMsg";
	
	protected User getSessionUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
	
	protected void setSessionUser(HttpServletRequest request , User user) {
		request.setAttribute(CommonConstant.USER_CONTEXT, user);
	}
	
	public final String getAppbaseUrl(HttpServletRequest request, String url) {
		Assert.hasLength(url, "url不能为空");
		Assert.isTrue(url.startsWith("/"), "必须以/打头");
		return request.getContextPath() + url;
	}
}
