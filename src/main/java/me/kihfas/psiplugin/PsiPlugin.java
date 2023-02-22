package me.kihfas.psiplugin;

import events.PlayerMove;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ConfigShit.CustomConfig;

public final class PsiPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();

        CustomConfig.setup();
        CustomConfig.get().addDefault("test", "pies");
        CustomConfig.get().options().copyDefaults(false);
        CustomConfig.reload();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new XPBottleBreakListener(), this);
        getServer().getPluginManager().registerEvents(new ShearSheepListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getCommand("god").setExecutor(new GodCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("psiGive").setExecutor(new GiveAStack());
    }

    @Override
    public void onDisable() {
        saveConfig();
        CustomConfig.save();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("printMessageFromConfig")) {
            if (sender instanceof Player) {
                Player pl = (Player) sender;
                String configString = getConfig().getString("Food");
                String thirdItem = getConfig().getStringList("FoodList").get(2);
                int configInt = getConfig().getInt("Number");
                pl.sendMessage(ChatColor.LIGHT_PURPLE + configString);
                pl.sendMessage(ChatColor.DARK_PURPLE + Integer.toString(configInt));
                pl.sendMessage(ChatColor.DARK_AQUA + thirdItem);
            }
        } else if (command.getName().equals("setFood")) {
            if (sender instanceof Player) {
                Player pl = (Player) sender;
                String food = args[0];
                getConfig().set("Food", food);
                pl.sendMessage(ChatColor.LIGHT_PURPLE + "food in config has been changed to: " + food);
            }
        }
        else if(command.getName().equals("customConfig")){
            if (sender instanceof Player) {
                    FileConfiguration config = CustomConfig.get();
                    Player pl = (Player) sender;
                    String message = args[0];
                    String nameOfPath = sender.getName() + ".message";
                    if(config.getString(nameOfPath)!=null){
                        config.set(nameOfPath, message);
                        pl.sendMessage("Succesfully changed your message!");
                    }
                    else{
                        config.createSection(nameOfPath);
                        config.set(nameOfPath, message);
                        pl.sendMessage("Succesfully added your message!");
                        config.addDefault(nameOfPath, message);
                    }
                    CustomConfig.save();
                    CustomConfig.reload();
                }
            }
        else if(command.getName().equals("getCustomConfig")){
            if (sender instanceof Player) {
                FileConfiguration config = CustomConfig.get();
                Player pl = (Player) sender;
                String nameOfPath = sender.getName() + ".message";
                pl.sendMessage("Message: " + config.getString(nameOfPath));
                pl.sendMessage("Test: " + config.getString("test"));
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println(event.getPlayer().getName() + " Joined!");
    }

    @EventHandler
    public void onLeaveBed(PlayerBedLeaveEvent e) {
        Player p = e.getPlayer();
        if (p.getName().equals("kihfas")) {
            p.sendMessage("live :)");
            p.setHealth(0.5);
        } else {
            p.sendMessage("kys");
            p.setHealth(0);
        }
    }
}
