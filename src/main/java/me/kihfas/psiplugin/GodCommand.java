package me.kihfas.psiplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player pl = (Player) sender;
            if(pl.getName().equals("kihfas")){
                if(pl.isInvulnerable()){
                    pl.setInvulnerable(false);
                    pl.sendMessage(ChatColor.RED + "god mode turned off");
                }
                else{
                    pl.setInvulnerable(true);
                    pl.sendMessage(ChatColor.AQUA + "god mode turned on");
                }
            }
        }

        return true;
    }
}
