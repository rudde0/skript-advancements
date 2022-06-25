package me.hotpocket.skriptadvancements;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.fren_gor.ultimateAdvancementAPI.AdvancementMain;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Skriptadvancements extends JavaPlugin {
    private static Skriptadvancements instance;
    private AdvancementMain main;
    private UltimateAdvancementAPI api;
    SkriptAddon addon;

    @Override
    public void onLoad() {
        main = new AdvancementMain(this);
        main.load();
    }

    public void onEnable() {
        instance = this;
        this.addon = Skript.registerAddon(this);
        this.addon.setLanguageFileDirectory("lang");

        main.enableSQLite(new File("database.db"));
        api = UltimateAdvancementAPI.getInstance(this);

        final int id = 15554;
        Metrics metrics = new Metrics(this, id);

        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()){
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        } else {
            reloadConfig();
            this.saveDefaultConfig();
        }

        try {
            this.addon.loadClasses("me.hotpocket.skriptadvancements", new String[]{"elements"});
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        Bukkit.getLogger().info("[skript-advancements] has been enabled.");
    }

    public void onDisable() {
        main.disable();
        Bukkit.getLogger().info("[skript-advancements] has been disabled.");
    }

    public static Skriptadvancements getInstance() {
        return instance;
    }
}
