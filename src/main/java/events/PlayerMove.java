package events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

        @EventHandler
        void onPlayerMove(PlayerMoveEvent event){
        Player pl = event.getPlayer();
        if(!pl.hasPermission("PsiPlugin.move")){
            event.setCancelled(true);
            pl.sendMessage(ChatColor.RED + " no permissions to move!");
        }

    }

}
