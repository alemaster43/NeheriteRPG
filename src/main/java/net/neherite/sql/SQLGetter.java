package net.neherite.sql;

import net.neherite.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLGetter {

    private Main plugin;

    public SQLGetter(Main plugin){
        this.plugin = plugin;
    }

    public void createTable(){
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerdata "
                    + "(NAME VARCHAR(100),UUID VARCHAR(100), CLASS VARCHAR(30), LEVEL INT(3), LEVELAB INT(1), POINTS INT(10), COOLDOWN INT(3), PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
/*
  CLASS - это класс
  LEVEL - уровень игроков
  LEVELAB - уровень способности
  POINTS - сколько очков у игрока
  COOLDOWN - перезарядка(фактическое ее сохранение, чтобы не абузить перезарядку)
*/
    public void createPlayer(Player player){
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO playerdata"
                        + " (NAME,UUID, CLASS, LEVEL, LEVELAB, POINTS, COOLDOWN) VALUES (?,?,?,?,?,?,?)");
                ps2.setString(1,player.getName()); //name
                ps2.setString(2, uuid.toString()); //uuid
                ps2.setString(3, uuid.toString()); //class
                ps2.setInt(4, 0); //level
                ps2.setInt(5, 0); //levelab
                ps2.setInt(6, 0); //points
                ps2.setInt(7, 0); //cooldown
                ps2.executeUpdate();

                return;

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean exists(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT * FROM playerdata WHERE UUID=?");
            ps.setString(1,uuid.toString());
            ResultSet results = ps.executeQuery();
            if(results.next()){
                return true;
            }
            return false;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //ВЗАИМОДЕЙСТВИЕ С КЛАССОМ
    public int getClass(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT CLASS FROM playerdata WHERE UUID=?");
            ps.setString(1,uuid.toString());
            ResultSet rs = ps.executeQuery();
            int classPlayer = 0;
            if(rs.next()){
                classPlayer = rs.getInt("CLASS");
                return classPlayer;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void setClass(UUID uuid,int classPlayer){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET CLASS=? WHERE UUID=?");
            ps.setInt(1,classPlayer);
            ps.setString(2,uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //ВЗАИМОДЕЙСТВИЕ С УРОВНЕМ
    public int getLevel(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT LEVEL FROM playerdata WHERE UUID=?");
            ps.setString(1,uuid.toString());
            ResultSet rs = ps.executeQuery();
            int level = 0;
            if(rs.next()){
                level = rs.getInt("LEVEL");
                return level;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void setLevel(UUID uuid,int level){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET LEVEL=? WHERE UUID=?");
            ps.setInt(1,level);
            ps.setString(2,uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    //ВЗАИМОДЕЙСТВИЕ С УРОВНЕМ СПОСОБНОСТЕЙ
    public int getLevelAB(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT LEVELAB FROM playerdata WHERE UUID=?");
            ps.setString(1,uuid.toString());
            ResultSet rs = ps.executeQuery();
            int levelab = 0;
            if(rs.next()){
                levelab = rs.getInt("LEVELAB");
                return levelab;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void setLevelAB(UUID uuid,int levelab){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET LEVELAB=? WHERE UUID=?");
            ps.setInt(1,levelab);
            ps.setString(2,uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //ВЗАИМОДЕЙСТВИЕ С ПОИНТАМИ
    public int getPoints(UUID uuid){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT POINTS FROM playerdata WHERE UUID=?");
            ps.setString(1,uuid.toString());
            ResultSet rs = ps.executeQuery();
            int points = 0;
            if(rs.next()){
                points = rs.getInt("POINTS");
                return points;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void setPoints(UUID uuid,int points){
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE playerdata SET POINTS=? WHERE UUID=?");
            ps.setInt(1,points);
            ps.setString(2,uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
