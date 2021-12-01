package net.neherite.managers;

import net.neherite.Main;
import org.bukkit.entity.Player;

import java.util.UUID;

public class LevelManager {
    private Main plugin;
    public LevelManager(Main plugin) {
        this.plugin = plugin;
    }

    public void setLevel(Player p, int amount){ //вроде как сделал установку поинтов
        UUID uuid = p.getUniqueId();
        if(plugin.data.getLevel(uuid) <= 300 && p.isOnline()) {
            plugin.data.setLevel(uuid, amount);
        } else if(plugin.data.getPoints(uuid) >= 301 && p.isOnline()) {
            p.sendMessage("Тут будет сообщение из конфига");
        }
    }
}
