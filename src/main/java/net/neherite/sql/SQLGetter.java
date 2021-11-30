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
                    + "(NAME VARCHAR(100),UUID VARCHAR(100), PRIMARY KEY (NAME))");
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void createPlayer(Player player){
        try {
            UUID uuid = player.getUniqueId();
            if(!exists(uuid)){
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT IGNORE INTO playerdata"
                        + " (NAME,UUID) VALUES (?,?)");
                ps2.setString(1,player.getName());
                ps2.setString(2, uuid.toString());
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

}
