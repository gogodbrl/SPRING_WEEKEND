package com.stone.infolabs.loginoutmanage.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.infolabs.boardmanage.dataservice.IMemberDAO;
import com.stone.infolabs.boardmanage.util.Result;

@Service
public class LogInOutManager implements ILogInOutManager {
	
	@Autowired
	IMemberDAO memberDAO;
	
	@Override
	public Result login(String id, String password) {
		Result result = new Result();
		String name = memberDAO.FindByIdAndPassword(id, password);
		if(name == null ) {
			boolean 존재여부 = memberDAO.isInById(id);
			if(존재여부 == false) { result.message = "존재하지 않는 아이디입니다."; }
			else { result.message = "아이디와 패스워드가 일치하지 않습니다."; }
			result.resultCode = -1;
		} else {
			result.value = name;
		}
		return result;
	}
}
