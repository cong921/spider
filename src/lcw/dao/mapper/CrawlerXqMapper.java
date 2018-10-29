package lcw.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import lcw.po.CrawlerXq;
import lcw.po.CrawlerXqExample;
import lcw.po.CrawlerXqWithBLOBs;

public interface CrawlerXqMapper {
    int countByExample(CrawlerXqExample example);

    int deleteByExample(CrawlerXqExample example);

    int deleteByPrimaryKey(Long xqId);

    int insert(CrawlerXqWithBLOBs record);

    int insertSelective(CrawlerXqWithBLOBs record);

    List<CrawlerXqWithBLOBs> selectByExampleWithBLOBs(CrawlerXqExample example);

    List<CrawlerXq> selectByExample(CrawlerXqExample example);

    CrawlerXqWithBLOBs selectByPrimaryKey(Long xqId);

    int updateByExampleSelective(@Param("record") CrawlerXqWithBLOBs record, @Param("example") CrawlerXqExample example);

    int updateByExampleWithBLOBs(@Param("record") CrawlerXqWithBLOBs record, @Param("example") CrawlerXqExample example);

    int updateByExample(@Param("record") CrawlerXq record, @Param("example") CrawlerXqExample example);

    int updateByPrimaryKeySelective(CrawlerXqWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CrawlerXqWithBLOBs record);

    int updateByPrimaryKey(CrawlerXq record);
}