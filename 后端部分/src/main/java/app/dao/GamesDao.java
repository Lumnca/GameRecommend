package app.dao;

import app.model.Game;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface GamesDao {
    @Select("SELECT * FROM games")
    List<Game> getAllGames();
    @Insert("INSERT INTO games (name, size, devType, date, good, imgUrl, info,state,href,label)" +
            " VALUES (#{name}, #{size}, #{devType}, #{date}, #{good}, #{imgUrl}, #{info},#{state},#{href},#{label})")
    int addGame(Game game);

}
