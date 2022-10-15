package me.hotpocket.skriptadvancements;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.fren_gor.ultimateAdvancementAPI.AdvancementMain;
import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import me.hotpocket.skriptadvancements.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SkriptAdvancements extends JavaPlugin implements Listener {

    private static SkriptAdvancements instance;
    public static AdvancementMain main;
    private UltimateAdvancementAPI api;
    private SkriptAddon addon;

    public static SkriptAdvancements getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        main = new AdvancementMain(this);
        main.load();
    }

    @Override
    public void onEnable() {
        instance = this;

        main.enableSQLite(new File("database.db"));
        api = UltimateAdvancementAPI.getInstance(this);

        Bukkit.getPluginManager().registerEvents(this, this);

        this.addon = Skript.registerAddon(this);
        this.addon.setLanguageFileDirectory("lang");

        final int id = 15554;
        new Metrics(this, id);

        try {
            this.addon.loadClasses("me.hotpocket.skriptadvancements", "elements");
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        main.disable();
    }

    @EventHandler
    private void onPlayerLoadingCompleted(PlayerLoadingCompletedEvent event) {
        for (AdvancementTab tab : api.getTabs())
            tab.updateEveryAdvancement(event.getPlayer());
    }
}
