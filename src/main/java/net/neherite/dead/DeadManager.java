package net.neherite.dead;

import net.neherite.Main;
import net.neherite.utils.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class DeadManager implements Listener {

    private Main plugin;
    public DeadManager(Main plugin){
        this.plugin = plugin;
    }



    @EventHandler
    public void onDeath(EntityDeathEvent e){
        if (e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            e.setCancelled(true);
            if(p.getLocation().getWorld().getName().equals("world_the_end")){
                p.sendMessage("§cВы погибли на §f" + (int)p.getLocation().getX() + "§f, " + (int)p.getLocation().getY()+  "§f, " + (int)p.getLocation().getZ() + "§c координатах.");
                p.sendMessage("§7");
                p.sendMessage("§сВ эндер мире нельзя потерять вещи");
                p.sendMessage("§спосле смерти.");
                p.sendMessage("§фВаши вещи сохранены!");
                p.sendMessage("§7");

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    p.playSound(p.getLocation(), Sound.ENTITY_WITCH_THROW,1,1);
                    //p.teleport(plugin.getConfig().getLocation("Locations.respawn"));
                    //p.openInventory(plugin.Deathinv);
                }, 5L);

            }else{

                try
                {
                    for (ItemStack itemStack : p.getInventory().getContents()) {
                        p.getWorld().dropItemNaturally(p.getLocation(), itemStack);
                        p.getInventory().removeItem(itemStack);
                    }
                    for (ItemStack itemStack : p.getInventory().getArmorContents()) {
                        p.getWorld().dropItemNaturally(p.getLocation(), itemStack);
                        p.getInventory().removeItem(itemStack);
                    }
                }
                catch(IllegalArgumentException em)
                {

                }

                p.sendMessage("§cВы погибли на §f" + (int)p.getLocation().getX() + "§f, " + (int)p.getLocation().getY()+  "§f, " + (int)p.getLocation().getZ() + "§c координатах.");

                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    p.getInventory().clear();
                    p.playSound(p.getLocation(), Sound.ENTITY_WITCH_THROW,1,1);
                    //p.teleport(plugin.loc);
                    //p.openInventory(plugin.Deathinv);
                }, 5L);
            }

        }
    }


 //   @EventHandler
   // public void onMove(PlayerMoveEvent e){
      //  Cuboid cb = new Cuboid(plugin.deathsloc1,plugin.deathsloc2);
   //     Player p = e.getPlayer();
       // if(cb.containsPlayer(p)){
         //   e.setCancelled(true);
            //if(!p.getInventory().equals(plugin.Deathinv))
           //     p.openInventory(plugin.Deathinv);
           // return;
   //     }
   // }

//    @EventHandler
//    public void onClick(InventoryClickEvent e){
//        Player p = (Player) e.getWhoClicked();
//        if (!e.getInventory().equals(plugin.Deathinv))
//            return;
//        if(e.getSlot() == 3){
//            if(p.getBedSpawnLocation() != null){
//                p.teleport(p.getBedSpawnLocation());
//                p.playSound(p.getLocation(),Sound.ENTITY_VILLAGER_YES,1,1);
//                p.sendMessage("§7");
//                p.sendMessage("§cВы возродились на своей кровати.");
//                p.sendMessage("§7");
//            }else{
//                p.teleport(plugin.spawnloc);
//                p.playSound(p.getLocation(),Sound.ENTITY_VILLAGER_NO,1,1);
//                p.sendMessage("§7");
//                p.sendMessage("§cК сожалению, у вас §fнет кровати§c,");
//                p.sendMessage("§cна которой вы могли бы возродиться.");
//                p.sendMessage("§7");
//                p.sendMessage("§cПоэтому я телепортировал вас на спавн.");
//                p.sendMessage("§7");
//            }
//        }
//        if(e.getSlot() == 5){
//
//            p.teleport(plugin.spawnloc);
//            p.sendMessage("§7");
//            p.sendMessage("§cВы возродились на спавне.");
//            p.sendMessage("§7");
//            p.playSound(p.getLocation(),Sound.ENTITY_VILLAGER_YES,1,1);
//        }
//        e.setCancelled(true);
//    }
//    @EventHandler
//    public void onInventoryClick(final InventoryDragEvent e) {
//        if (e.getInventory().equals(plugin.Deathinv)) {
//            e.setCancelled(true);
//        }
//    }

}
