package com.chetan.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.chetan.dao.UserDao;
import com.chetan.daoimpl.UserDaoImpl;

public class TestUserDao {
	static UserDao daoImpl=new UserDaoImpl(0);
	@BeforeClass
	public static void addItemTest() {
		assertEquals(true,daoImpl.orderItem());
	}
	@Test
	public void testUserItem() {
		assertEquals(true,daoImpl.displayItem());
	}


}
