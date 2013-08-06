package com.kyl.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyl.dao.IBaseDao;
import com.kyl.domain.User;
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private IBaseDao<User> baseDao;
	
	public void save(User user) {
		try {
			baseDao.saveChsEntity(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
