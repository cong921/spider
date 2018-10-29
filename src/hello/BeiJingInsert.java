package hello;

import java.util.ArrayList;
import java.util.List;

import lcw.po.CrawlerXqWithBLOBs;

public class BeiJingInsert extends SqlsessionInsert {
	private List<CrawlerXqWithBLOBs> list=new ArrayList<>();
	
	public List<CrawlerXqWithBLOBs> getList() {
		return  list;
	}


	@Override
	public void domethod() {
		for (CrawlerXqWithBLOBs exchange : list) {
			sqlSession.insert("lcw.dao.mapper.CrawlerXqMapper.insert", exchange);
		}
		
	}

	public void setList(List<CrawlerXqWithBLOBs> list1) {
		this.list = list1;
		
	}

}
