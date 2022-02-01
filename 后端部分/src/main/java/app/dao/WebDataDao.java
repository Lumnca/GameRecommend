package app.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface WebDataDao {
    @Select("SELECT visitorNumber,(SELECT count(*) FROM web.games) as gameNumber FROM web.web_data ")
    JSONObject getWebData();
    @Update("UPDATE web_data SET visitorNumber = visitorNumber+1  WHERE (`id` = '1')")
    int VisitorNumberAdd();
}
