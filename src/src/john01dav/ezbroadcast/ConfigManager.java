package src.john01dav.ezbroadcast;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import src.john01dav.ezbroadcast.broadcastsets.BroadcastSet;
import src.john01dav.ezbroadcast.broadcastsets.PermissionedBroadcastSet;
import src.john01dav.ezbroadcast.broadcastsets.StandardBroadcastSet;

import java.util.List;

public class ConfigManager{
    private EzBroadcast ezBroadcast;

    protected ConfigManager(EzBroadcast ezBroadcast){
        this.ezBroadcast = ezBroadcast;
    }

    protected void reloadConfig(){
        FileConfiguration config;

        for(BroadcastSet broadcastSet : ezBroadcast.getBroadcastSetArrayList()){
            broadcastSet.stop();
        }
        ezBroadcast.getBroadcastSetArrayList().clear();

        ezBroadcast.saveDefaultConfig();
        config = ezBroadcast.getConfig();

        for(String broadcastSetName : config.getConfigurationSection("broadcastsets").getKeys(false)){
            String type = config.getString("broadcastsets." + broadcastSetName + ".type").toLowerCase();
            String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("broadcastsets." + broadcastSetName + ".prefix"));
            int interval = (int) Math.round(config.getDouble("broadcastsets." + broadcastSetName + ".interval") * 20.0);

            List<String> messages = config.getStringList("broadcastsets." + broadcastSetName + ".messages");
            String[] formattedMessages = new String[messages.size()];

            BroadcastSet broadcastSet;

            for(int x=0;x<messages.size();x++){
                formattedMessages[x] = prefix + ChatColor.translateAlternateColorCodes('&', messages.get(x));
            }

            switch(type){
                case "standard":
                    broadcastSet = new StandardBroadcastSet(formattedMessages, broadcastSetName.toLowerCase(), interval);
                    break;
                case "permissioned":
                    broadcastSet = new PermissionedBroadcastSet(formattedMessages, broadcastSetName.toLowerCase(), interval);
                    break;
                default:
                    ezBroadcast.getLogger().warning("Can't load broadcast set " + broadcastSetName + ". Unknown broadcast type " + type);
                    continue;
            }

            ezBroadcast.getBroadcastSetArrayList().add(broadcastSet);
            broadcastSet.start(ezBroadcast, 0);
        }
    }

}
