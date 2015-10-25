package src.john01dav.ezbroadcast.broadcastsets;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import src.john01dav.ezbroadcast.PluginUtil;

/**
 * An instance of this class represents a set of messages to
 * be broadcasted and handles broadcasting those messages
 */
public abstract class BroadcastSet{
    private final String name;
    private final String[] messages;
    private final int interval;
    private int index;

    private BukkitTask bukkitTask = null;

    /**
     * Creates a new broadcast set
     * @param messages What messages should be contained in this set?
     * @param name What is this set called?
     * @param interval How often, in ticks, should this set broadcast?
     */
    public BroadcastSet(String[] messages, String name, int interval){
        this.messages = new String[messages.length]; //copying array to avoid modifications to it after initialization
        this.name = name;
        this.interval = interval;

        PluginUtil.copyArray(messages, this.messages);
        index = 0;
    }

    /**
     * Broadcasts the given message to whomever it should appear to
     * @param message The message to broadcast
     */
    protected abstract void broadcastMessage(String message);

    private class BroadcastSetBroadcaster extends BukkitRunnable{ //private class to avoid run() being called externally
        public void run(){
            String message = messages[index];

            broadcastMessage(message);

            if(++index >= messages.length){
                index = 0;
            }
        }
    }

    /**
     * Schedules the start of this broadcast set
     * @param plugin What plugin should this broadcast set register it's task with?0
     * @param delay How long in the future, in ticks, should this broadcast set begin broadcasting?
     */
    public void start(JavaPlugin plugin, int delay){
        bukkitTask = new BroadcastSetBroadcaster().runTaskTimer(plugin, delay, interval);
    }

    /**
     * Stops this broadcast set and resets it state (ie. current message) to the default (ie. the first message)
     */
    public void stop(){
        bukkitTask.cancel();

        //to reset the state to the default
        bukkitTask = null;
        index = 0;
    }

    /**
     * Gets the name of this broadcast set
     * @return the name of this broadcast set
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the interval, in ticks, at which this broadcast set broadcasts
     * @return
     */
    public int getInterval(){
        return interval;
    }

    public int getIndex(){
        return index;
    }

}
