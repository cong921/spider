package hello;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

public class TotalPageUtilProcessor implements PageProcessor {
	// http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin=10&PageCond/length=6&PageCond/isCount=true
	public static final String URL_LIST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.index\\.index\\.moreEveryoneAnswerQuery\\.biz\\.ext\\?PageCond/begin=(\\d)+&pageCond/length=6&PageCond/isCount=true";
	public static final String URL_POST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger = Logger.getLogger(TotalPageUtilProcessor.class);
	private static Integer totalPage;

	public static Integer getTotalPage() {
		if(totalPage==null) {
			Spider spider = Spider.create(new TotalPageUtilProcessor());

			Request request = new Request(
					"http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin=0&PageCond/length=6&PageCond/isCount=true");
			HttpRequestBody requestBody = new HttpRequestBody();
			requestBody.setBody(("{\"PageCond/begin\":0,\"PageCond/length\":6,\"PageCond/isCount\":\"true\"}").getBytes());
			request.setRequestBody(requestBody);
			request.setMethod(HttpConstant.Method.POST);
			spider.addRequest(request);
			spider.run();
		}
		return totalPage;
	}

	public static void setTotalPage(Integer totalPage) {
		TotalPageUtilProcessor.totalPage = totalPage;
	}

	private Site site = Site.me().setDomain("http://banshi.beijing.gov.cn").setSleepTime(300).setUserAgent(
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

			JSONObject json = (JSONObject) JSONObject.parse(page.getRawText());
			Object object = json.getJSONObject("PageCond").get("totalPage");
			totalPage = Integer.parseInt(object.toString());

	}

}
