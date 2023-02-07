package me.kihfas.psiplugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class ShearSheepListener implements Listener {
    @EventHandler
    public void onShearSheep(PlayerShearEntityEvent e){
        Player pl = e.getPlayer();
        Entity sheep = e.getEntity();
        if(sheep.getName().equals("pies")){
            pl.sendMessage("Leave pies alone!");
            e.setCancelled(true);
        }
    }
}
