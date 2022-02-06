package app.server.file;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileDao {
    @Insert("INSERT INTO file (`fileaddr`, `date`, `size`, `type`) VALUES (#{fileaddr},#{date},#{size},#{type})")
    int addFile(File file);
    @Select("SELECT * FROM file")
    List<File> getAllFiles();
    @Delete("DELETE FROM file WHERE (`id` = #{id})")
    int delFile(Integer id);
}
