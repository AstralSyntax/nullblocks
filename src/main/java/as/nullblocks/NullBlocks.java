package as.nullblocks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NullBlocks extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getConfig();
        getServer().getPluginManager().registerEvents(this, this);
        FileConfiguration config = this.getConfig();
        config.addDefault("DisableBlockUpdates", true);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        if (getConfig().getBoolean("DisableBlockUpdates")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stoplag");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "halt-activity confirm");
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[NullBlocks] Block Updating is Disabled!");
        } else {
            getLogger().warning("Block Updating is Enabled!");
        }
    }
}