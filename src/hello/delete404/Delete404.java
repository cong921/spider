package hello.delete404;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import hello.shanghai.pipleline.ShanghaiPipleline3;
import hello.shanghai.pipleline.ShanghaiPipleline3_detail;
import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqExample.Criteria;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import util.JdbcUtil;
import util.MyBatisUtil;

public class Delete404 implements PageProcessor, Job {
	// public static final String URL_LIST =
	// http://service.shanghai.gov.cn/pagemore/iframePagerIndex_12344_2_22.html?objtype=2&nodeid=12344&page=12&pagesize=22
	public static final String URL_LIST = "http://service\\.shanghai\\.gov\\.cn/pagemore/iframePagerIndex_4411_3_30\\.html\\?objtype=\\d+&nodeid=\\d+&page=\\d+&pagesize=\\d+";
	public static final String URL_POST = "http://www\\.shanghai\\.gov\\.cn/nw2/nw2314/nw2315/nw4411/[a-zA-Z0-9]+.html";
	// public static final String URL_POST =http://www.shanghai.gov.cn/nw2/nw2314/nw2315/nw4411/u21aw1265496.html
	// "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger = Logger.getLogger(Delete404.class);
	private Site site = Site.me().setDomain("http://zwdt.sh.gov.cn").setSleepTime(800).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			;

	public void setSite(Site site) {
		this.site = site;
	}
	
	public static void main(String[] args) {//ShanghaiPipleline3
		Spider addUrl = Spider.create(new Delete404()).addPipeline(new ConsolePipeline());
		Spider addUrl2=null;

		JdbcUtil jdbcUtil = new JdbcUtil();
		jdbcUtil.getConnection();
		try {
			List<Map<String, Object>> result = jdbcUtil.findResult(
					"select xq_url from crawler_xq", null);
			for (Map<String, Object> m : result) {
				addUrl2=addUrl.addUrl(m.get("XQ_URL").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.releaseConn();
		}
	
		addUrl2.thread(100).setDownloader(new HttpClientDownloader1()).run();
				
//		Spider.create(new ShanghaiPageProcessorIframe()).addPipeline(new ConsolePipeline()).addUrl(
//				"http://www.shanghai.gov.cn/nw2/nw2314/nw2319/nw12344/u26aw1171.html")
//		.run();
		
	}
	private static boolean isFailUrl(String url) {
		boolean b=false;
		try {
			
			BufferedReader br=new BufferedReader(new FileReader(new File("C:/root/kencery/quartz/warn/all.output.log")));
			String line=null;
			while((line=br.readLine())!=null) {
				if(line.indexOf(url)>0) {
					b=line.endsWith("error");
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
		
	}

	@Override
	public Site getSite() {

		return site;
	}

	@Override
	public void process(Page page) {}

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

