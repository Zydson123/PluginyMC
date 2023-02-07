package me.kihfas.psiplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player pl = (Player) sender;
            if(pl.hasPermission("PsiPlugin.feed")){
                pl.setFoodLevel(20);
                pl.sendMessage(ChatColor.GREEN +"no more hunger!");
            }
            else{
                pl.sendMessage(ChatColor.RED +"no permissions (PsiPlugin.feed needed)");
            }
        }
        else{
            System.out.printf("You cant be fed!");
        }
        return true;
    }
}