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

public class AskAndAnswerProcessor implements PageProcessor, Job {
	// http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin=10&PageCond/length=6&PageCond/isCount=true
	public static final String URL_LIST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.index\\.index\\.moreEveryoneAnswerQuery\\.biz\\.ext\\?PageCond/begin=(\\d)+&pageCond/length=6&PageCond/isCount=true";
	public static List<String> failUrl = new ArrayList<String>();
	public static final String URL_POST = "http://rexian\\.beijing\\.gov\\.cn/default/com\\.web\\.consult\\.consultDetail\\.flow\\?originalId=[a-zA-z0-9]+";
	private static Logger logger = Logger.getLogger(AskAndAnswerProcessor.class);
	private Site site = Site.me().setDomain("http://banshi.beijing.gov.cn").setSleepTime(10).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");;

	public void setSite(Site site) {
		this.site = site;
	}

	public static void main(String[] args) {
		Spider spider = Spider.create(new AskAndAnswerProcessor());

//		 for (int m = 0; m <= 100; m++) {
		for (int m = 0; m <= TotalPageUtilProcessor.getTotalPage(); m++) {
			// System.out.println(TotalPageUtilProcessor.getTotalPage());
			String begin = m == 0 ? "0" : m + "0";
			Request request = new Request(
					"http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin="
							+ begin + "&PageCond/length=6&PageCond/isCount=true");
			HttpRequestBody requestBody = new HttpRequestBody();
			requestBody
					.setBody(("{\"PageCond/begin\":" + begin + ",\"PageCond/length\":6,\"PageCond/isCount\":\"true\"}")
							.getBytes());
			request.setRequestBody(requestBody);
			request.setMethod(HttpConstant.Method.POST);
			spider.addRequest(request);
		}
		spider.addPipeline(new AskAndAnswerPipleline()).run();

	}

	@Override
	public Site getSite() {

		return site;
	}

	@Override
	public void process(Page page) {
		if (page.getUrl().regex(URL_LIST).match()) {

			JSONObject json = (JSONObject) JSONObject.parse(page.getRawText());
			JSONArray jsonArray = json.getJSONArray("everyoneAnswer");
			for (Object object2 : jsonArray) {
				JSONObject andAnswer = (JSONObject) object2;
				// System.out.println(andAnswer.get("letterTitle"));
				// System.out.println(andAnswer.get("originalId"));
				page.addTargetRequest(
						"http://rexian.beijing.gov.cn/default/com.web.consult.consultDetail.flow?originalId="
								+ andAnswer.get("originalId"));
			}
			// List<JSONObject> jsonList = json.toList(JSONObject.class);
			// System.out.println(jsonList);
			// List<List<Object>> list = new ArrayList<>();
			// for (JSONObject json2 : jsonList) {
			// List<Object> list1 = new ArrayList<Object>();
			// list1.add(json2.getString("zn_url"));
			// list1.add(json2.getString("xz_type"));
			// list1.add(json2.getString("title"));
			// list1.add(json2.getString("server_object"));
			// list1.add(json2.getString("zt_type"));
			// list1.add(json2.getString("dx_type"));
			// list1.add(json2.getString("zq_type"));
			// list1.add(json2.getString("bm_type"));
			// list.add(list1);
			// logger.info("+++++" + list1);
			// }
			// page.putField("list", list);

		} else {
			String url = page.getResultItems().getRequest().getUrl();
//			System.out.println(url);
			page.putField("title", page.getHtml().xpath("//h2/text()").get());
			page.putField("answer", page.getHtml().xpath("//p[contains(@class, 'offic_p font14')]/text()").get());
			page.putField("url", url);
			// System.out.println("==================");
			// System.out.println(page.getResultItems());
			logger.info(page.getResultItems());
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Spider spider = Spider.create(new AskAndAnswerProcessor());

		// for (int m = 0; m <= 0; m++) {
		for (int m = 0; m <= TotalPageUtilProcessor.getTotalPage(); m++) {
			// System.out.println(TotalPageUtilProcessor.getTotalPage());
			String begin = m == 0 ? "0" : m + "0";
			Request request = new Request(
					"http://rexian.beijing.gov.cn/default/com.web.index.index.moreEveryoneAnswerQuery.biz.ext?PageCond/begin="
							+ begin + "&PageCond/length=6&PageCond/isCount=true");
			HttpRequestBody requestBody = new HttpRequestBody();
			requestBody
					.setBody(("{\"PageCond/begin\":" + begin + ",\"PageCond/length\":6,\"PageCond/isCount\":\"true\"}")
							.getBytes());
			request.setRequestBody(requestBody);
			request.setMethod(HttpConstant.Method.POST);
			spider.addRequest(request);
		}
		spider.addPipeline(new AskAndAnswerPipleline()).run();

	}
}
