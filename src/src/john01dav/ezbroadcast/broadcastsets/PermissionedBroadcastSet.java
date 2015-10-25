package src.john01dav.ezbroadcast.broadcastsets;

import org.bukkit.Bukkit;

public class PermissionedBroadcastSet extends BroadcastSet{

    public PermissionedBroadcastSet(String[] messages, String name, int interval){
        super(messages, name, interval);
    }

    @Override
    protected void broadcastMessage(String message){
        Bukkit.getServer().broadcast(message, "ezbroadcast.set." + getName());
        Bukkit.getConsoleSender().sendMessage(message);
    }

}
