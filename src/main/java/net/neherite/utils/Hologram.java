package net.neherite.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hologram implements Listener {

//Создать голограмму
    public void createhologram(Location location, String text,int tick, Plugin plugin){
        PotionEffect pe = new PotionEffect(PotionEffectType.LEVITATION,40,2,true,false);
        ArmorStand as = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);

        as.setGravity(true);
        as.setCanPickupItems(false);
        as.setCustomName(text);
        as.setCustomNameVisible(true);
        as.setVisible(false);
        as.addPotionEffect(pe);
        //Удалить голограмму, через n тиков
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                as.remove();
            }
        }, tick);
    }

    @EventHandler
    public void manipulate(PlayerArmorStandManipulateEvent e)
    {
        if(!e.getRightClicked().isVisible())
        {
            e.setCancelled(true);
        }
    }

}
