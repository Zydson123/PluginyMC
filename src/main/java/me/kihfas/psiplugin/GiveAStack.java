package me.kihfas.psiplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class GiveAStack implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player pl = (Player) sender;
            if(pl.isOp()){
               Player target = Bukkit.getPlayerExact(args[0]);
               if(target instanceof Player){
                       Material material = org.bukkit.Material.getMaterial(args[1]);
                        if(material instanceof Material){
                            target.getInventory().addItem(new ItemStack(material, 64));
                        }
                        else{
                            pl.sendMessage(ChatColor.RED + "that is not a real block!");
                        }
               }
               else{
                   pl.sendMessage(ChatColor.RED + "this player does not exist");
               }
            }
            else{
                pl.sendMessage(ChatColor.BOLD + (ChatColor.RED + "You don't have the permissions to use this command!"));
            }
        }
        return true;
    }
}
