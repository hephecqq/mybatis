package com.entities;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisTest2 {

	public static void main(String[] args) {
		SqlSessionFactory factory = MybatisTest.getSession();
		SqlSession session = factory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			User user = userOperation.selectUserByID(1);
			System.out.println(user.getUserAddress());
			System.out.println(user.getUserName());
			
			
			
			
		} catch (Exception e) {
		} finally {
			session.close();
		}

	}
}
