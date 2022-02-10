package app.dao;

import app.model.Game;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface GamesDao {
    @Select("SELECT * FROM games")
    List<Game> getAllGames();
    @Insert("INSERT INTO games (name, size, devType, date, good, imgUrl, info,state,href,label,imgs)" +
            " VALUES (#{name}, #{size}, #{devType}, #{date}, #{good}, #{imgUrl}, #{info},#{state},#{href},#{label},#{imgs})")
    int addGame(Game game);

    @Delete("DELETE FROM `web`.`games` WHERE (`id` =  #{id})")
    int deleteGame(Integer id);

    @Update("UPDATE games SET `name` = #{name}, `size` = #{size}, `devType` = #{devType}, " +
            "`good` = #{good}, `imgUrl` = #{imgUrl}, `info` = #{info}, `state` = #{state}, `href` = #{href}, " +
            "`imgs` = #{imgs},`label` = #{label} WHERE (`id` = #{id})")
    int updateGame(Game game);

    @Update("UPDATE games SET good = good+1 WHERE (`id` =  #{id})")
    int addGood(Integer id);
}
