package hello.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import hello.shanghai.pipleline.ShanghaiPipleline3;
import hello.shanghai.pipleline.ShanghaiPiplelinePictureNews;
import hello.shanghai.pipleline.ShanghaiPipleline_nw15343;
import hello.shanghai.pipleline.ShanghaiPipleline_nw18454;
import hello.shanghai.pipleline.ShanghaiPipleline_nw31406;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class LawNpc implements PageProcessor, Job {
	// public static final String URL_LIST =
	// http://service.shanghai.gov.cn/pagemore/iframePagerIndex_12344_2_22.html?objtype=2&nodeid=12344&page=12&pagesize=22
	//http://service.shanghai.gov.cn/pagemore/iframePagerIndex_5827_3_16.html?objtype=3&nodeid=5827&page=12&pagesize=16
	public static final String URL_LIST = "http://service\\.shanghai\\.gov\\.cn/pagemore/iframePagerIndex_31406_3_30\\.html\\?objtype=\\d+&nodeid=\\d+&page=\\d+&pagesize=\\d+";
//	public static final String URL_LIST = "http://service\\.shanghai\\.gov\\.cn/pagemore/iframePagerIndex_5827_3_16.html\\?objtype=\\d+&nodeid=\\d+&page=\\d+&pagesize=\\d+";
	public static final String URL_POST = "http://www\\.shanghai\\.gov\\.cn/nw2/nw2314/nw2315/nw18454/[a-zA-Z0-9]+\\.html";
//											http://www.shanghai.gov.cn/nw2/nw2314/nw2315/nw18454/u21aw1295656.html
	// public static final String URL_POST =http://www.shanghai.gov.cn/nw2/nw2314/nw2315/nw4411/u21aw1265496.html
	// "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger = Logger.getLogger(LawNpc.class);
	private Site site = Site.me().setDomain("http://service.shanghai.gov.cn").setSleepTime(150).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			;

	public void setSite(Site site) {
		this.site = site;
	}

	public static void main(String[] args) {
		Spider addUrl = Spider.create(new LawNpc()).addPipeline(new ConsolePipeline());
		Spider addUrl2=null;//1170
			String url="http://law.npc.gov.cn/FLFG/index/xianfamore.jsp";
//			if(isFailUrl(url))
			  addUrl2 = addUrl.addUrl(url);
		
		addUrl2.thread(1).run();
	
		/*while(true) {
			Spider addUrl = Spider.create(new ShanghaiPageProcessor_nw31406()).addPipeline(new ShanghaiPipleline_nw31406());
			Spider addUrl2=null;//1170
//			int j=713;
			for(int i=1;i<=14100;i++) {
				String url="http://service.shanghai.gov.cn/pagemore/iframePagerIndex_31406_3_30.html?objtype=3&nodeid=31406&page="+i+"&pagesize=30";
//				if(isFailUrl(url))
				  addUrl2 = addUrl.addUrl(url);
			}
			addUrl2.thread(1).run();
		}*/
				
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
		System.out.println(page);
//		System.out.println(page.getUrl());
//		System.out.println(URL_LIST);
//		System.out.println(page.getUrl());
//		syso
//		System.out.println(page.getUrl());
//		System.out.println(page.getUrl().regex(URL_POST).match());
		if (page.getUrl().regex(URL_LIST).match()) {
//			System.out.println(page.getHtml().links().regex(URL_POST).all());\
//			System.out.println(page.getHtml());
			Document parse = Jsoup.parse(page.getHtml().toString());
			Elements elementsByTag = parse.select("#Form1 > ul > li ");
//			System.out.println(elementsByTag);
//			System.out.println("*****************");
			List<Map<String,String>> listMap=new ArrayList<Map<String,String>>();
			
			for (Element element : elementsByTag) {
				
				Elements select = element.select("a");
				Elements timeSpan = element.select("span");
				
				 String title = select.attr("title");
				String href = select.attr("href");
				String val = timeSpan.text(); 
//				System.out.println(val);
				Map<String,String> map=new HashMap<String,String>();
				map.put("title", title);
				map.put("href", href);
				map.put("time", val);
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
