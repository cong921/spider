package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.text.DefaultStyledDocument.ElementBuffer;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Request;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hello.bean.SHPersonService;
import hello.shanghai.pipleline.ShanghaiPipleline1;
import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqExample.Criteria;
import lcw.po.CrawlerXqWithBLOBs;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import util.MyBatisUtil;
//http://www.shanghai.gov.cn/nw2/nw2314/nw2319/nw41149/nw41150/index.html
public class ShanghaiPageProcessorIframe implements PageProcessor, Job {
	// public static final String URL_LIST =
	// "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.index\\.index\\.moreEveryoneAnswerQuery\\.biz\\.ext\\?PageCond/begin=(\\d)+&pageCond/length=6&PageCond/isCount=true";
	public static final String URL_LIST = "http://service\\.shanghai\\.gov\\.cn/pagemore/iframePagerIndex1\\.aspx\\?page=\\d+&name=&Suserprop8=&Euserprop8=&Suserprop2=&Euserprop2=&Suserprop3=&Euserprop3=&userprop1=&userprop4=&objtype=\\d+&pagesize=\\d+&nodeid=\\d+";
	public static final String URL_POST = "http://www\\.shanghai\\.gov\\.cn/nw2/nw2314/nw2319/nw41149/[a-zA-Z0-9-]+\\.html";
	// public static final String URL_POST =
	// "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger = Logger.getLogger(ShanghaiPageProcessorIframe.class);
	private Site site = Site.me().setDomain("http://zwdt.sh.gov.cn").setSleepTime(500).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			.addHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
			.addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN,zh;q=0.9")
			.addHeader("Connection", "keep-alive").addHeader("Cookie", "ASP.NET_SessionId=2s45to5dgh2hmeoftujsfe5i")
			.addHeader("Host", "service.shanghai.gov.cn")
			.addHeader("Referer",
					"http://service.shanghai.gov.cn/pagemore/iframePagerIndex1.aspx?page=1&name=&Suserprop8=&Euserprop8=&Suserprop2=&Euserprop2=&Suserprop3=&Euserprop3=&userprop1=&userprop4=&objtype=4&pagesize=15&nodeid=41149")
			.addHeader("Upgrade-Insecure-Requests", "1")
			.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");

	public void setSite(Site site) {
		this.site = site;
	}

	public static void main(String[] args) {
		Spider addPipeline = Spider.create(new ShanghaiPageProcessorIframe()).addPipeline(new ShanghaiPipleline1());
		Spider addUrl=null;
		for(int i=1;i<=15;i++) {
			 addUrl = addPipeline.addUrl(
					"http://service.shanghai.gov.cn/pagemore/iframePagerIndex1.aspx?page="+i+"&name=&Suserprop8=&Euserprop8=&Suserprop2=&Euserprop2=&Suserprop3=&Euserprop3=&userprop1=&userprop4=&objtype=4&pagesize=15&nodeid=41149");
					
		}
		addUrl.run();
//		Spider.create(new ShanghaiPageProcessorIframe()).addPipeline(new ConsolePipeline()).addUrl(
//				"http://www.shanghai.gov.cn/nw2/nw2314/nw2319/nw12344/u26aw1171.html")
//		.run();
		
	}

	@Override
	public Site getSite() {

		return site;
	}

	@Override
	public void process(Page page) {
//		System.out.println(page.getUrl());
//		System.out.println(URL_POST);
//		System.out.println(page.getUrl());
//		System.out.println(page.getUrl().regex(URL_POST).match());
		if (page.getUrl().regex(URL_LIST).match()) {
//			System.out.println(page.getHtml().links().regex(URL_POST).all());\
//			System.out.println(page.getHtml());
			Document parse = Jsoup.parse(page.getHtml().toString());
			Elements elementsByTag = parse.select(" table > tbody > tr > td.limit-a.tdupdate > a");
//			System.out.println("*****************");
			List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
			for (Element element : elementsByTag) {
				 String title = element.attr("title");
				String href = element.attr("href");
				Map<String,String> map=new HashMap<String,String>();
				map.put("title", title);
				map.put("href", href);
				listMap.add(map);
			}
			if(listMap!=null) page.putField("listMap", listMap);
		} 

	}

	/*
	 * public void process(Page page) { Json json = page.getJson();
	 * System.out.println(json.toString()); JSONObject
	 * jsonObject=JSONObject.parseObject(json.toString()); JSONObject jsonArray =
	 * jsonObject.getJSONObject("HashMap").getJSONObject("allMap");
	 * Set<Entry<String, Object>> entrySet = jsonArray.entrySet(); //
	 * System.out.println(jsonArray.get); List<List<SHPersonService>> list =new
	 * ArrayList<>(); for (Entry<String, Object> entry : entrySet) {
	 * 
	 * List<SHPersonService> parseArray =
	 * JSON.parseArray(entry.getValue().toString(),SHPersonService.class);
	 * list.add(parseArray);
	 * 
	 * } page.putField("list", list);
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// Spider.create(new
		// ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50750.json?_=1507532502499").addPipeline(new
		// BeiJingPipleline()).run();
		// Spider.create(new
		// ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50748.json?_=1507532502499").addPipeline(new
		// BeiJingPipleline()).run();
		// Spider.create(new
		// ShanghaiPageProcessor()).addUrl("http://banshi.beijing.gov.cn/jsonData/201710/t20171007_50746.json?_=1507532502499").addPipeline(new
		// BeiJingPipleline()).run();

	}
}
