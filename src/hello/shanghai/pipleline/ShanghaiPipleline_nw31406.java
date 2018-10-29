package hello.shanghai.pipleline;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hello.BeiJingInsert;
import hello.MD5Utils;
import hello.SqlsessionInsert;
import hello.bean.SHPersonService;
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
public class ShanghaiPipleline_nw31406 extends FilePersistentBase implements Pipeline {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * private static ConnectionPool connectionPool = new
	 * ConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/spider",
	 * "root", "root");
	 * 
	 *//**
		 * create a FilePipeline with default path"/data/webmagic/"
	 * @throws ParseException 
		 *//*
			 * public JubiPipleline() { setPath("/data/webmagic/"); }
			 * 
			 * public JubiPipleline(String path) { setPath(path); }
			 */
	public Date formatDate(String date) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		return sdf.parse(date);
			
	}
	@Override
	public void process(ResultItems resultItems, Task task) {
//      System.out.println(resultItems);
        List<Map<String,String>> list = resultItems.get("listMap");
        if(list==null) return;
        SqlsessionInsert taxesInsert=null;
        List<CrawlerXqWithBLOBs> list1 = new ArrayList<>();
        for (Map<String, String> map : list) {
            String title = map.get("title");
            String url = map.get("href");
            Date time =null;
            try {
            	time= formatDate(map.get("time"));
//            	System.out.println(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(title!=null) {
                 taxesInsert = new BeiJingInsert();
                 


                CrawlerXqWithBLOBs exchange = new CrawlerXqWithBLOBs();
                exchange.setXqUrl(url);
                exchange.setXqUrlMd5(MD5Utils.createMD5(url));
                exchange.setXqPudate(time);
                exchange.setXqTitle(title);
                exchange.setZdName("上海市政府");
                exchange.setLmName("行业新闻");
                exchange.setSjfl("要闻动态");
                exchange.setXqContent(null);
                exchange.setLoadTime(new Date());
                exchange.setIsSimtitle(0);;
                exchange.setIsExteurl(0);
                exchange.setLmTop("要闻动态");
                exchange.setSta(200);
//              System.out.println("++++++++++++++=============");
//              System.out.println(exchange);
//              System.out.println("++++++++++++++=============");
                CrawlerXqExample example = new CrawlerXqExample();
                Criteria createCriteria = example.createCriteria();
                createCriteria.andXqUrlMd5EqualTo(exchange.getXqUrlMd5());
                List<CrawlerXq> selectByExample=new ArrayList<>();
                try {
                    selectByExample= MyBatisUtil.getSqlSession().getMapper(CrawlerXqMapper.class)
                            .selectByExample(example);
                } finally {
                    if(MyBatisUtil.getSqlSession()!=null)
                    MyBatisUtil.getSqlSession().close();
                }
                if (selectByExample.size() < 1)
                    list1.add(exchange);
            
            
                
            }
        }
        taxesInsert.setList(list1);
        try {
            taxesInsert.insert();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }}
/*	public void process(ResultItems resultItems, Task task) {
		List<List<SHPersonService>> list = resultItems.get("list");
		SqlsessionInsert taxesInsert = new BeiJingInsert();
		List<CrawlerXqWithBLOBs> list1 = new ArrayList<>();
		for (List<SHPersonService> listStr : list) {
			for (SHPersonService shPersonService : listStr) {
				
				CrawlerXqWithBLOBs exchange = new CrawlerXqWithBLOBs();
				exchange.setXqUrl("http://zwdt.sh.gov.cn/zwdtSW/bsfw/showDetail.do?ST_ID=" + shPersonService.getStId()+"&ST_DIC_DESC="+shPersonService.getStDicDesc());
				exchange.setXqUrlMd5(MD5Utils.createMD5(exchange.getXqUrl()));
				exchange.setXqPudate(null);
				exchange.setXqTitle(shPersonService.getStSubitemName());
				exchange.setZdName("上海市政府");
				exchange.setLmName("个人服务");
				exchange.setSjfl("办事服务");
				exchange.setXqContent(exchange.getXqUrl());
				exchange.setLoadTime(new Date());
				exchange.setIsSimtitle(0);;
				exchange.setIsExteurl(0);
				exchange.setLmTop("网上政务大厅");
				exchange.setSta(200);
//				System.out.println("++++++++++++++=============");
//				System.out.println(listStr);
//				System.out.println(exchange);
//				System.out.println("++++++++++++++=============");
				CrawlerXqExample example = new CrawlerXqExample();
				Criteria createCriteria = example.createCriteria();
				createCriteria.andXqUrlMd5EqualTo(exchange.getXqUrlMd5());
				List<CrawlerXq> selectByExample=new ArrayList<>();
				try {
					selectByExample= MyBatisUtil.getSqlSession().getMapper(CrawlerXqMapper.class)
							.selectByExample(example);
				} finally {
					if(MyBatisUtil.getSqlSession()!=null)
						MyBatisUtil.getSqlSession().close();
				}
				if (selectByExample.size() < 1)
					list1.add(exchange);
				
			}
		}
		taxesInsert.setList(list1);
		try {
			taxesInsert.insert();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
*/}



