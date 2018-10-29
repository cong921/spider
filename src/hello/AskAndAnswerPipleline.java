package hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqWithBLOBs;
import lcw.po.CrawlerXqExample.Criteria;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;
import util.MyBatisUtil;

/**
 * Store results in files.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
@ThreadSafe
public class AskAndAnswerPipleline extends FilePersistentBase implements Pipeline {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * private static ConnectionPool connectionPool = new
	 * ConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/spider",
	 * "root", "root");
	 * 
	 *//**
		 * create a FilePipeline with default path"/data/webmagic/"
		 *//*
			 * public JubiPipleline() { setPath("/data/webmagic/"); }
			 * 
			 * public JubiPipleline(String path) { setPath(path); }
			 */

	@Override
	public void process(ResultItems resultItems, Task task) {
//		 System.out.println("resultItmes:" + resultItems);
		if (resultItems.get("title") == null)
			return;
		SqlsessionInsert taxesInsert = new BeiJingInsert();
		List<CrawlerXqWithBLOBs> list1 = new ArrayList<CrawlerXqWithBLOBs>();
		CrawlerXqWithBLOBs exchange = new CrawlerXqWithBLOBs();
		Object object = resultItems.get("title");
		exchange.setXqUrl(resultItems.get("url"));
		exchange.setXqUrlMd5(MD5Utils.createMD5(resultItems.get("url").toString()));
		exchange.setXqPudate(null);
		exchange.setXqTitle(resultItems.get("title").toString());
		exchange.setZdName("首都之窗");
		exchange.setLmName("咨询问答");
		exchange.setSjfl("典型案例");
		exchange.setIsSimtitle(0);
		;
		exchange.setIsExteurl(0);
		exchange.setSjfl("典型案例");
		exchange.setXqContent(resultItems.get("answer").toString());
		exchange.setLoadTime(new Date());

//		 System.out.println("++++++++++++++=============");
//		 System.out.println(exchange);
//		 System.out.println("++++++++++++++=============");
		try {
			CrawlerXqExample example = new CrawlerXqExample();
			Criteria createCriteria = example.createCriteria();
			createCriteria.andXqUrlMd5EqualTo(exchange.getXqUrlMd5());
			List<CrawlerXq> selectByExample = MyBatisUtil.getSqlSession().getMapper(CrawlerXqMapper.class)
					.selectByExample(example);

			if (selectByExample.size() < 1)
				list1.add(exchange);
		} finally {
			if(MyBatisUtil.getSqlSession()!=null) {
				MyBatisUtil.getSqlSession().close();
			}
		}
		if(list1!=null&&list1.size()!=0) {
			taxesInsert.setList(list1);
			try {
				taxesInsert.insert();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
