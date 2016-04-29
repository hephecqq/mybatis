package com.entities;

import java.io.Reader;
import java.util.List;

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
	
			////查询结果为一个list
			List<User> list=session.selectList("entities.selectUsers","%e%");
			for (User user2 : list) {
				System.out.println("& "+user2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
}
