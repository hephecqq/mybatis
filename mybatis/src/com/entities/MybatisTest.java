package com.entities;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		try {
			reader = Resources.getResourceAsReader("configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public static void main(String[] args) {
		SqlSession session=sqlSessionFactory.openSession();
		try {
			User user=session.selectOne("entities.selectUserByID",1);
			System.out.println(user);
			System.out.println(user.getUserAddress());
			System.out.println(user.getUserName());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
}
