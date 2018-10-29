package hello;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSONObject;

import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqExample.Criteria;
import lcw.po.CrawlerXqWithBLOBs;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import util.MyBatisUtil;

public class BeijingPageProcessor implements PageProcessor,Job {
	private static Logger logger= Logger.getLogger(BeijingPageProcessor.class) ;
	private Site site=Site
    .me()
    .setDomain("http://banshi.beijing.gov.cn")
    .setSleepTime(300)
    .setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");;
	
	public void setSite(Site site) {
		this.site = site;
	}

	public static void main(String[] args) {
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50750.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50748.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50746.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
//		CrawlerXqWithBLOBs selectOne = MyBatisUtil.getSqlSession().selectOne("lcw.dao.CrawlerXqMapper.selectByXqUrlMd5", "0cb76ba53c9bdb6d4140b301f2c83e65");
//		CrawlerXqWithBLOBs crawlerXqWithBLOBs=new CrawlerXqWithBLOBs();
//		crawlerXqWithBLOBs.setXqUrlMd5("0cb76ba53c9bdb6d4140b301f2c83e65");
//		crawlerXqWithBLOBs.setXqId(12829l);
//		CrawlerXqExample example=new CrawlerXqExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.andXqUrlMd5EqualTo("0cb76ba53c9bdb6d4140b301f2c83e65");
//		List<CrawlerXq> selectByExample = MyBatisUtil.getSqlSession().getMapper(CrawlerXqMapper.class).selectByExample(example);
//		System.out.println(selectByExample.size());
		
	}
	
	@Override
	public Site getSite() {
		
		return site;
	}

	@Override
	public void process(Page page) {
		Json json = page.getJson();
    	List<JSONObject> jsonList=json.toList(JSONObject.class);
    	List<List<Object>> list=new ArrayList<>();
    	for (JSONObject json2 : jsonList) {
    		List<Object> list1=new ArrayList<Object>();
    		list1.add(json2.getString("zn_url"));
    		list1.add(json2.getString("xz_type"));
    		list1.add(json2.getString("title"));
    		list1.add(json2.getString("server_object"));
    		list1.add(json2.getString("zt_type"));
    		list1.add(json2.getString("dx_type"));
    		list1.add(json2.getString("zq_type"));
    		list1.add(json2.getString("bm_type"));
    		list.add(list1);
    		logger.info("+++++"+list1);
		}
    	page.putField("list", list);
		
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50750.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50748.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		Spider.create(new BeijingPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50746.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		
	}
}
