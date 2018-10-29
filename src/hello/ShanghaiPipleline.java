package hello;

import java.io.IOException;
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
import lcw.dao.mapper.CrawlerXqMapper;
import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqExample.Criteria;
import lcw.po.CrawlerXqWithBLOBs;
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
public class ShanghaiPipleline extends FilePersistentBase implements Pipeline {

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
//		System.out.println(resultItems);
		String url=null;
		if(resultItems.get("url")!=null)
			url = resultItems.get("url").toString();
		else
			return;
		String title = resultItems.get("title").toString();
		if(title!=null) {
			SqlsessionInsert taxesInsert = new BeiJingInsert();
			List<CrawlerXqWithBLOBs> list1 = new ArrayList<>();


			CrawlerXqWithBLOBs exchange = new CrawlerXqWithBLOBs();
			exchange.setXqUrl(url);
			exchange.setXqUrlMd5(MD5Utils.createMD5(url));
			exchange.setXqPudate(null);
			exchange.setXqTitle(title);
			exchange.setZdName("上海市政府");
			exchange.setLmName("个人服务");
			exchange.setSjfl("办事服务");
			exchange.setXqContent(exchange.getXqUrl());
			exchange.setLoadTime(new Date());
			exchange.setIsSimtitle(0);;
			exchange.setIsExteurl(0);
			exchange.setLmTop("网上政务大厅");
			exchange.setSta(200);
//			System.out.println("++++++++++++++=============");
//			System.out.println(listStr);
//			System.out.println(exchange);
//			System.out.println("++++++++++++++=============");
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
		
		
			taxesInsert.setList(list1);
			try {
				taxesInsert.insert();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	

    }
/*  public void process(ResultItems resultItems, Task task) {
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
//              System.out.println("++++++++++++++=============");
//              System.out.println(listStr);
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
        }
        
    }
*/}
