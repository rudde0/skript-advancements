package me.hotpocket.skriptadvancements;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.hotpocket.skriptadvancements.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SkriptAdvancements extends JavaPlugin {
    private static SkriptAdvancements instance;
    SkriptAddon addon;

    public void onEnable() {
        instance = this;
        this.addon = Skript.registerAddon(this);
        this.addon.setLanguageFileDirectory("lang");

        final int id = 15554;
        new Metrics(this, id);

        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()){
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        } else {
            reloadConfig();
            this.saveDefaultConfig();
        }

        try {
            this.addon.loadClasses("me.hotpocket.skriptadvancements", "elements");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        Bukkit.getLogger().info("[skript-advancements] has been enabled.");
    }

    public void onDisable() {
        Bukkit.getLogger().info("[skript-advancements] has been disabled.");
    }

    public static SkriptAdvancements getInstance() {
        return instance;
    }
}
