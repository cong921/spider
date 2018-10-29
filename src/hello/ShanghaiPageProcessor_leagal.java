package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Connection.Request;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hello.bean.SHPersonService;
import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqExample.Criteria;
import lcw.po.CrawlerXqWithBLOBs;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import util.MyBatisUtil;

public class ShanghaiPageProcessor_leagal implements PageProcessor,Job {
//	public static final String URL_LIST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.index\\.index\\.moreEveryoneAnswerQuery\\.biz\\.ext\\?PageCond/begin=(\\d)+&pageCond/length=6&PageCond/isCount=true";
	public static final String URL_LIST = "http://zwdt\\.sh.gov\\.cn/zwdtSW/bsfw/showItemList.do\\?pageSize=10&pageNumber=\\d+&ST_ORG_REGION=SH00SH&ST_ITEM_FNAME=all&ST_ITEM_TYPE=all&ST_NET=&ST_FEATURE=&ST_KEYWORD=&SORT=DEFAULT&ST_ITEM_CODE=&GFType=\\w";
	public static final String URL_POST = "http://zwdt.sh\\.gov\\.cn/zwdtSW/bsfw/showDetail\\.do\\?ST_ID=[a-zA-z0-9-]+&ST_DIC_DESC=[0-9]+";
//	public static final String URL_POST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger= Logger.getLogger(ShanghaiPageProcessor_leagal.class) ;
	private Site site=Site
    .me()
    .setDomain("http://zwdt.sh.gov.cn")
    .setSleepTime(100)
    .setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	
	public void setSite(Site site) {
		this.site = site;
	}

	public static void main(String[] args) {
		
		Spider addPipeline = Spider.create(new ShanghaiPageProcessor_leagal()).addPipeline(new ShanghaiPipleline_leagal());
		Spider addUrl = null;
		Integer totalPage = SHTotalPageUtilProcessor2.getTotalPage("http://zwdt.sh.gov.cn/zwdtSW/bsfw/showItemList.do?pageSize=10&pageNumber=1&ST_ORG_REGION=SH00SH&ST_ITEM_FNAME=all&ST_ITEM_TYPE=all&ST_NET=&ST_FEATURE=&ST_KEYWORD=&SORT=DEFAULT&ST_ITEM_CODE=&GFType=f");
		System.out.println(totalPage);
		for (int i=1;i<=totalPage;i++) {
			 addUrl = addPipeline.addUrl("http://zwdt.sh.gov.cn/zwdtSW/bsfw/showItemList.do?pageSize=10&pageNumber="+String.valueOf(i)+"&ST_ORG_REGION=SH00SH&ST_ITEM_FNAME=all&ST_ITEM_TYPE=all&ST_NET=&ST_FEATURE=&ST_KEYWORD=&SORT=DEFAULT&ST_ITEM_CODE=&GFType=f");
		}
		/*Integer totalPage1 = SHTotalPageUtilProcessor.getTotalPage("http://zwdt.sh.gov.cn/zwdtSW/bsfw/showItemList.do?pageSize=10&pageNumber=1&ST_ORG_REGION=SH00SH&ST_ITEM_FNAME=all&ST_ITEM_TYPE=all&ST_NET=&ST_FEATURE=&ST_KEYWORD=&SORT=DEFAULT&ST_ITEM_CODE=&GFType=f");
//		 totalPage1 = 1;
		
		for (int i=1;i<=totalPage1;i++) {
			addUrl = addPipeline.addUrl("http://zwdt.sh.gov.cn/zwdtSW/bsfw/showItemList.do?pageSize=10&pageNumber="+String.valueOf(i)+"&ST_ORG_REGION=SH00SH&ST_ITEM_FNAME=all&ST_ITEM_TYPE=all&ST_NET=&ST_FEATURE=&ST_KEYWORD=&SORT=DEFAULT&ST_ITEM_CODE=&GFType=f");
		}*/
		
		if(addUrl!=null)
			addUrl.run();
//		Spider.create(new ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50748.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
//		Spider.create(new ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50746.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
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
//		System.out.println(getSite());
		
		if(page.getJson()!=null&&page.getJson().toString().startsWith("{\"HashMap\"")) {
			Json json = page.getJson();
//			System.out.println(json.toString());
			JSONObject jsonObject=JSONObject.parseObject(json.toString());
			JSONObject jsonArray = jsonObject.getJSONObject("HashMap").getJSONObject("allMap");
			Set<Entry<String, Object>> entrySet = jsonArray.entrySet();
//			System.out.println(jsonArray.get);
			List<List<SHPersonService>> list =new ArrayList<>();
			for (Entry<String, Object> entry : entrySet) {
				
				List<SHPersonService> parseArray = JSON.parseArray(entry.getValue().toString(),SHPersonService.class);
				for (SHPersonService shPersonService : parseArray) {
					page.addTargetRequest("http://zwdt.sh.gov.cn/zwdtSW/bsfw/showDetail.do?ST_ID="+shPersonService.getStId()+"&ST_DIC_DESC="+shPersonService.getStDicDesc());
				}
				
			}
		}else if(page.getUrl().regex(URL_POST).match())  {
				SHPersonService shPersonService=new SHPersonService();
//				System.out.println("_________________________________________-_-_____________");
//				System.out.println(page.getHtml().xpath("//div[contains(@class, 'bsmx_left4')]/p[1]/text()"));
			    page.putField("title", page.getHtml().xpath("//*[@id=\"main\"]/div[2]/div[4]/p[2]/text()"));
			    page.putField("url", page.getUrl());
//			    System.out.println(page.getResultItems());
		}
		
		
		
		
	}
/*	public void process(Page page) {
		Json json = page.getJson();
		System.out.println(json.toString());
		JSONObject jsonObject=JSONObject.parseObject(json.toString());
		JSONObject jsonArray = jsonObject.getJSONObject("HashMap").getJSONObject("allMap");
		Set<Entry<String, Object>> entrySet = jsonArray.entrySet();
//		System.out.println(jsonArray.get);
		List<List<SHPersonService>> list =new ArrayList<>();
		for (Entry<String, Object> entry : entrySet) {
			
			List<SHPersonService> parseArray = JSON.parseArray(entry.getValue().toString(),SHPersonService.class);
			list.add(parseArray);
			
		}
		page.putField("list", list);
		
		
		
		
	}
*/
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		Spider.create(new ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50750.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
//		Spider.create(new ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50748.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
//		Spider.create(new ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50746.json?_=1507532502499").addPipeline(new BeiJingPipleline()).run();
		
	}
}
