package src.john01dav.ezbroadcast.broadcastsets;

import org.bukkit.Bukkit;

public class StandardBroadcastSet extends BroadcastSet{

    public StandardBroadcastSet(String[] messages, String name, int interval){
        super(messages, name, interval);
    }

    @Override
    protected void broadcastMessage(String message){
        Bukkit.getServer().broadcastMessage(message);
    }

}
