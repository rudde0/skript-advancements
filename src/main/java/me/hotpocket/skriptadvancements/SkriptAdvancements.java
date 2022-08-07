package me.hotpocket.skriptadvancements;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import ch.njol.skript.aliases.ItemType;
import com.fren_gor.ultimateAdvancementAPI.AdvancementMain;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import me.hotpocket.skriptadvancements.bstats.Metrics;
import me.hotpocket.skriptadvancements.listeners.JoinListener;
import me.hotpocket.skriptadvancements.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SkriptAdvancements extends JavaPlugin {
    private static SkriptAdvancements instance;
    private AdvancementMain main;
    private UltimateAdvancementAPI api;

    @Override
    public void onLoad() {
        main = new AdvancementMain(this);
        main.load();
    }

    SkriptAddon addon;

    public void onEnable() {
        instance = this;

        main.enableSQLite(new File("database.db"));
        api = UltimateAdvancementAPI.getInstance(this);

        this.addon = Skript.registerAddon(this);
        this.addon.setLanguageFileDirectory("lang");

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);

        final int id = 15554;
        new Metrics(this, id);

        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()) {
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

        new UpdateChecker(SkriptAdvancements.getInstance(), 96702).getVersion(version -> {
            if (!SkriptAdvancements.getInstance().getDescription().getVersion().equals(version)) {
                sendConsole("§eA new version of §lSkript-Advancements §eis available!\n§bDownload Here:§r https://www.spigotmc.org/resources/skript-advancements-skript-addon.96702/");
            }
        });

        sendConsole("&ahas been enabled!");
    }

    public void onDisable() {
        main.disable();
    }

    public static SkriptAdvancements getInstance() {
        return instance;
    }

    public static ItemStack typeToStack(ItemType type) {
        ItemStack item = new ItemStack(type.getMaterial());
        item.setItemMeta(type.getItemMeta());
        return item;
    }

    public static NamespacedKey getKey(String key) {
        return new NamespacedKey(getInstance().getConfig().getString("custom-key"), key);
    }

    public static void sendConsole(String message) {
        getInstance().getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&bSKRIPT-ADVANCEMENTS&9] &f" + message));
    }
}
