package me.kihfas.psiplugin;

import events.PlayerMove;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public final class PsiPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Plugin startup logic
        System.out.println("Piesio enabled :)");

        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new XPBottleBreakListener(),this);
        getServer().getPluginManager().registerEvents(new ShearSheepListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerMove(),this);
        getCommand("god").setExecutor(new GodCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("psiGive").setExecutor(new GiveAStack());
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("printMessageFromConfig")){
            if(sender instanceof Player){
                Player pl = (Player) sender;
                String configString = getConfig().getString("Food");
                String thirdItem = getConfig().getStringList("FoodList").get(2);
                int configInt = getConfig().getInt("Number");
                pl.sendMessage(ChatColor.LIGHT_PURPLE + configString);
                pl.sendMessage(ChatColor.DARK_PURPLE + Integer.toString(configInt));
                pl.sendMessage(ChatColor.DARK_AQUA + thirdItem);
            }
        }
        else if(command.getName().equals("setFood")) {
            if (sender instanceof Player) {
                Player pl = (Player) sender;
                String food = args[0];
                getConfig().set("Food", food);
                pl.sendMessage(ChatColor.LIGHT_PURPLE + "food in config has been changed to: " + food);
            }
        }
        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        System.out.println(event.getPlayer().getName() + " Joined!");
    }

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent e){
        Player p = e.getPlayer();
        if(p.getName().equals("kihfas")){
            p.sendMessage("live :)");
            p.setHealth(0.5);
        }
        else{
            p.sendMessage("kys");
            p.setHealth(0);
        }
    }
}
