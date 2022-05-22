package ch.browni.doublejump;

import ch.browni.doublejump.DoubleJumpListeners.DoubleJumpListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new DoubleJumpListener(),this);

    }

    @Override
    public void onDisable() {

    }
}
