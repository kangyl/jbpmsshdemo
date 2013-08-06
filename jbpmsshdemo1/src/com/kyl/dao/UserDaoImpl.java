package com.kyl.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kyl.domain.User;

	
	public class UserDaoImpl implements UserDao {

		
		HibernateSupport hibernateSupport;
		
		public void save(User user) throws Exception {
			hibernateSupport.saveEntity(user);
			
		}

}
