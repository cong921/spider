package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hello.bean.SHPersonService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;

public class SHTotalPageUtilProcessor implements PageProcessor {
	// http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin=10&PageCond/length=6&PageCond/isCount=true
	// public static final String URL_LIST =
	// "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.index\\.index\\.moreEveryoneAnswerQuery\\.biz\\.ext\\?PageCond/begin=(\\d)+&pageCond/length=6&PageCond/isCount=true";
	// public static final String URL_POST =
	// "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger = Logger.getLogger(SHTotalPageUtilProcessor.class);
	private static Integer totalPage;
	public static Integer getTotalPage(String url) {
		if (totalPage == null) {
			Spider.create(new SHTotalPageUtilProcessor()).addUrl(url)
					.addPipeline(new ConsolePipeline()).run();
		}
		return totalPage;
	}

	public static void setTotalPage(Integer totalPage) {
		SHTotalPageUtilProcessor.totalPage = totalPage;
	}

	private Site site = Site.me().setDomain("http://zwdt.sh.gov.cn").setSleepTime(300).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");;

	public void setSite(Site site) {
		this.site = site;
	}

	@Override
	public Site getSite() {

		return site;
	}

	@Override
	public void process(Page page) {
		Json json = page.getJson();
		System.out.println(json.toString());
		JSONObject jsonObject = JSONObject.parseObject(json.toString());
		JSONArray jsonArray2 = jsonObject.getJSONObject("HashMap").getJSONArray("pageU");
		Object object = jsonArray2.get(jsonArray2.size()-1);

		totalPage = Integer.parseInt(object.toString());

	}

	public static void main(String[] args) {
		System.out.println(getTotalPage("http://zwdt.sh.gov.cn/zwdtSW/bsfw/showItemList.do?pageSize=10&pageNumber=1&ST_ORG_REGION=SH00SH&ST_ITEM_FNAME=all&ST_ITEM_TYPE=all&ST_NET=&ST_FEATURE=&ST_KEYWORD=&SORT=DEFAULT&ST_ITEM_CODE=&GFType=f"));
	}
}
