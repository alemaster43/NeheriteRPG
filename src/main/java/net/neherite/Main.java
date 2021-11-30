package net.neherite;

import net.neherite.managers.DeadManager;
import net.neherite.managers.PointsManager;
import net.neherite.sql.MySQL;
import net.neherite.sql.SQLGetter;
import net.neherite.utils.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    private static Main instance;

    public MySQL SQL;
    public SQLGetter data;

    public PointsManager pm;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;


        Bukkit.getPluginManager().registerEvents(new Hologram(),this);
        Bukkit.getPluginManager().registerEvents(new DeadManager(this),this);

        this.pm = new PointsManager(this);


        //База данных MySQL
        //Начало
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info(ChatColor.RED + "NeheriteRPG:В config.yml введи нормально данные MySQL");

        }
        if(SQL.isConnected()){
            Bukkit.getLogger().info(ChatColor.GREEN + "NeheriteRPG: Воу! Всё работает, база данных подключилась!");
            data.createTable();
        }
        //Конец
        //База данных MySQL


    }

    @Override
    public void onDisable() {

    }

}
