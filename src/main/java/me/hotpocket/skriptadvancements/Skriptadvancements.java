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
    private static AdvancementMain main;
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

        final int id = 15554;
        Metrics metrics = new Metrics(this, id);

        // Enable UltimateAdvancementAPI using file based database (SQLite)
        // You can use other enable<DatabaseMode>() methods, like enableMySQL(), instead of the next line.
        main.enableSQLite(new File("database.db"));
        // After the initialisation of AdvancementMain you can get an instance of the UltimateAdvancementAPI class
        api = UltimateAdvancementAPI.getInstance(this);


        try {
            this.addon.loadClasses("me.hotpocket.skriptadvancements", new String[]{"elements"});
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        Bukkit.getLogger().info("[skript-advancements] has been enabled.");
    }

    public void onDisable() {
        Bukkit.getLogger().info("[skript-advancements] has been disabled.");
    }

    public static Skriptadvancements getInstance() {
        return instance;
    }
}
