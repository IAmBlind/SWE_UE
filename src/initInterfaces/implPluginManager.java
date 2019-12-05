package initInterfaces;

import Interface.Plugin;
import Interface.PluginManager;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import initInterfaces.helpers.naviPlugin;
import initInterfaces.helpers.toLowerPlugin;
import initInterfaces.helpers.temperaturePlugin;
import initInterfaces.helpers.staticPlugin;


public class implPluginManager implements PluginManager {

    List<Plugin> pluginList;

    public implPluginManager(){
        pluginList = new ArrayList<Plugin>();
        // List of all plugins
        pluginList.add(new naviPlugin());
        pluginList.add(new temperaturePlugin());
        pluginList.add(new staticPlugin());
        pluginList.add(new toLowerPlugin());
        //pluginList.add();
    }

    @Override
    public Iterable<Plugin> getPlugins() {
        return pluginList;
        //return null;
    }

    @Override
    public void add(String plugin) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        /*try{
            //TODO: Plugin by class
            Plugin _plugin = ;

            if(_plugin != null){
                pluginList.add(_plugin);
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

        String PluginName = "";
        try {
            BufferedReader bufferedReader = new BufferedReader((Reader) pluginList);
            while (true){
                PluginName = bufferedReader.readLine();
                if(PluginName == null) break;
                Class<?> clazz = Class.forName("initInterfaces.helpers." + PluginName);
                pluginList.add((Plugin) clazz.getConstructor().newInstance());
            }
            bufferedReader.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        pluginList.clear();
    }

    @Override
    public void add(Plugin plugin) {
        pluginList.add(plugin);
    }

    // Plugin selector
    public Plugin select(Request req){
        return null;
    }
}
