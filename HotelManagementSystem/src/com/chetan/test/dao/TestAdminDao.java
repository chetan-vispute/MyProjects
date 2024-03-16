package com.chetan.test.dao;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chetan.dao.AdminDao;
import com.chetan.daoimpl.AdminDaoImpl;

public class TestAdminDao {
	static AdminDao adminDaoImpl=new AdminDaoImpl();
	@BeforeClass
	public void addItemTest() {
		assertEquals(true,adminDaoImpl.addItem());
	}
	@Test
	public void updateItemTest() {
		assertEquals(true,adminDaoImpl.updateItem());
	}
	
	@AfterClass
	public void deleteItemTest() {
		assertEquals(true,adminDaoImpl.deleteItem());
	}

}
