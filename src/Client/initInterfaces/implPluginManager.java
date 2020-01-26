package Client.initInterfaces;

import Client.Interface.Plugin;
import Client.Interface.PluginManager;

import java.util.LinkedList;
import java.util.List;

import Client.initInterfaces.helpers.naviPlugin;
import Client.initInterfaces.helpers.toLowerPlugin;
import Client.initInterfaces.helpers.temperaturePlugin;
import Client.initInterfaces.helpers.staticPlugin;
import Client.initInterfaces.helpers.implPlugin;


public class implPluginManager implements PluginManager {

    private List<Plugin> pluginList = new LinkedList<>();

    public implPluginManager(){
        // List of all plugins
        pluginList.add(new naviPlugin());
        pluginList.add(new temperaturePlugin());
        pluginList.add(new staticPlugin());
        pluginList.add(new toLowerPlugin());
        pluginList.add(new implPlugin());
    }

    /**
     * Returns a list of all plugins. Never returns null.
     * TODO: Refactor to List<Plugin>, Enumeration is deprecated
     * @return
     */
    @Override
    public Iterable<Plugin> getPlugins() {
        if(pluginList.isEmpty()){
            pluginList.add(new implPlugin());
        }
        return pluginList;
    }

    /**
     * Adds a new plugin by class name. If the plugin was already added, nothing will happen.
     * Throws an exeption, when the type cannot be resoled or the class does not implement Plugin.
     * @param plugin
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        //TODO: Plugin by class
        Class<?> myClass = Class.forName(plugin);

        if (Plugin.class.isAssignableFrom(myClass)) {
            try {
                Plugin _plugin = (Plugin) myClass.getDeclaredConstructor().newInstance();
                pluginList.add(_plugin);
            } catch (Exception e) {
                throw new ClassNotFoundException("Class doesn't exist");
            }
        } else {
            throw new ClassNotFoundException("Class doesn't exist");
        }
    }

    /**
     * Clear all Plugins
     */
    @Override
    public void clear() {
        pluginList.clear();
    }

    /**
     * Adds a new plugin. If the plugin was already added, nothing will happen.
     * @param plugin
     */
    @Override
    public void add(Plugin plugin) {
        pluginList.add(plugin);
    }

    // Plugin selector
    public Plugin selectPlugin(Request request) throws Exception {
        float handle = 0;
        Plugin plugin = null;
        for (Plugin pl : pluginList) {
            if (pl.canHandle(request) > handle) {
                handle = pl.canHandle(request);
                plugin = pl;
            }
        }
        return plugin;
    }

    public Plugin selectBestPlugin(Request request){
        Plugin response = null;
        float max = 0;
        for(Plugin pl : pluginList){
            float num = pl.canHandle(request);
            if(num > max){
                max = num;
                response = pl;
            }
        }
        return response;
    }

}
