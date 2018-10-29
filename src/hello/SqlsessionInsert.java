package hello;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import lcw.po.CrawlerXqWithBLOBs;

public abstract class SqlsessionInsert {
	protected SqlSession sqlSession;
	{
		sqlSession = getSqlSession();
	}

	public synchronized SqlSession getSqlSession() {
		 
		if(sqlSession==null) {
			String resource = "config/mybatis-config.xml";
			InputStream inputStream = null;
			try {
				inputStream = Resources.getResourceAsStream(resource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
			return sqlSessionFactory.openSession();
		}
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Test
	public void insert() throws IOException {

		try {
			domethod();
		} finally {
			if (sqlSession != null)
				this.sqlSession.commit();
			if (sqlSession != null)
				this.sqlSession.close();
		}

	}

	public abstract void domethod();

	public abstract void setList(List<CrawlerXqWithBLOBs> list1);
}
