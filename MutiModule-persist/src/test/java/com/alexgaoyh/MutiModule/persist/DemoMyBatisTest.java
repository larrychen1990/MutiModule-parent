package com.alexgaoyh.MutiModule.persist;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.alexgaoyh.MutiModule.domain.demo.DemoEntity;
import com.alexgaoyh.MutiModule.persist.demo.DemoMapper;

public class DemoMyBatisTest {
	
	private  SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void prepare() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	@Test
	public void testInsert() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			DemoMapper demoMapper = sqlSession.getMapper(DemoMapper.class);
			DemoEntity demo = new DemoEntity();
			demo.setName("test");
			demoMapper.insertDemo(demo);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlSession.close();
	}
	
	@Test
	public void testSelectById() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			DemoMapper demoMapper = sqlSession.getMapper(DemoMapper.class);
			DemoEntity demo = demoMapper.selectDemoById(2);
			if(demo != null) {
				System.out.println(demo.getName());
			}
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlSession.close();
	}

}
