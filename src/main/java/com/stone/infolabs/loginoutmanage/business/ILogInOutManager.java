package com.stone.infolabs.loginoutmanage.business;

import org.springframework.stereotype.Component;

import com.stone.infolabs.boardmanage.util.Result;

@Component
public interface ILogInOutManager {
	public Result login(String id, String password );
}
