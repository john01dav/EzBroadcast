package src.john01dav.ezbroadcast;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import src.john01dav.ezbroadcast.broadcastsets.BroadcastSet;

import java.util.ArrayList;

public class EzBroadcast extends JavaPlugin{
    private ArrayList<BroadcastSet> broadcastSetArrayList;
    private ConfigManager configManager;

    @Override
    public void onEnable(){
        broadcastSetArrayList = new ArrayList<>();

        configManager = new ConfigManager(this);
        configManager.reloadConfig();

        getLogger().info("EzBroadcast has been enabled!");
    }

    @Override
    public void onDisable(){
        getLogger().info("EzBroadcast has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("ezbroadcastreload")){
            configManager.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "All broadcast sets have been reloaded.");
        }
        return true;
    }

    public ArrayList<BroadcastSet> getBroadcastSetArrayList(){
        return broadcastSetArrayList;
    }

}
