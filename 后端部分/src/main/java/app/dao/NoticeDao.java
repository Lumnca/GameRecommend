package app.dao;

import app.model.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeDao {
    @Select("SELECT * FROM notice")
    List<Notice> getAllNotice();
}
