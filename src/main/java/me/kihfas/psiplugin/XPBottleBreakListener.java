package me.kihfas.psiplugin;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class XPBottleBreakListener implements Listener {

    @EventHandler
    private void onXPBottleBreak(ExpBottleEvent e){

        e.setExperience(2000);
    }

}
