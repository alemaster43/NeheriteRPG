package net.neherite.managers;

import net.neherite.Main;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PointsManager {
    private Main plugin;
    public PointsManager(Main plugin) { this.plugin = plugin; }


    public void setPoints(Player p, int amount){ //вроде как сделал установку поинтов
        UUID uuid = p.getUniqueId();
        if(plugin.data.getPoints(uuid) <= 100000 && p.isOnline()) {
            plugin.data.setPoints(uuid, amount);
        } else if(plugin.data.getPoints(uuid) >= 100000 && p.isOnline()) {
            p.sendMessage(Main.getInstance().getConfig().getString("messages.playerfullpoints"));
            p.sendMessage(Main.getInstance().getDataFolder()+"messages.playerfullpoints");
        }
    }

    public void delPoints(Player p){ //вроде как сделал удаление поинтов
        int amount = 0;
        UUID uuid = p.getUniqueId();
        plugin.data.setPoints(uuid, amount);
    }
}
