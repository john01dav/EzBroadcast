package src.john01dav.ezbroadcast;

public final class PluginUtil{

    private PluginUtil(){}

    /**
     * Copys the contents from one array to another
     * @param from The array to copy from
     * @param to The array to copy to
     * @param <T> The type of the arrays
     */
    public static <T> void copyArray(T[] from, T[] to){
        for(int x=0;x<from.length;x++){
            to[x] = from[x];
        }
    }

}
