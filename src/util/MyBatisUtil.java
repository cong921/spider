package util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	protected static SqlSession sqlSession;
	{sqlSession=getSqlSession();}
	public synchronized static SqlSession getSqlSession() {
		
		if(sqlSession==null) {
			String resource="config/mybatis-config.xml";
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory.openSession();
		}
		return sqlSession;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
