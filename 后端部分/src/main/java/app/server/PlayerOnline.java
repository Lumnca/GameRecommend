package app.server;

import app.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerOnline {
    private List<Player> players;
    public PlayerOnline(){
        System.out.println("初始化在线玩家数据！");
        players = new ArrayList<>();
    }
    public void allPlayerOffOnline(){
        for (Player p:players
             ) {
            p.setState(0);
        }
    }
    public void addPlayer(Player player){
        if(player.getId()==null){
            player.setId(players.size());
        }
        players.add(player);
    }


    public Player getPlayerById(Integer id){
        for (Player p:players) {
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }



    public List<Player> getPlayers() {
        List<Player> ps = new ArrayList<>();
        for (Player p:players
        ) {
           if(p.getState()>0){
               ps.add(p);
           }
        }
        return ps;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setPotision(Player player){
        this.getPlayerById(player.getId()).setX(player.getX());
        this.getPlayerById(player.getId()).setY(player.getY());
    }
}
