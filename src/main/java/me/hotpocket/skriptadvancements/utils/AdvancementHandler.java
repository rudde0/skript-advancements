package me.hotpocket.skriptadvancements.utils;

import me.hotpocket.skriptadvancements.SkriptAdvancements;
import me.hotpocket.skriptadvancements.utils.AdvancementAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class AdvancementHandler {

    public static String name;
    public static String title = "Advancement";
    public static String description = "Custom advancement made with Skript-Advancements";
    public static ItemStack icon = new ItemStack(Material.STONE_PICKAXE);
    public static Material background = Material.DIRT;
    public static AdvancementAPI.Frame frame = AdvancementAPI.Frame.TASK;
    public static NamespacedKey parent = NamespacedKey.minecraft("adventure/root");
    public static boolean autoUnlock = true;
    public static boolean hidden = true;
    public static boolean toast = true;
    public static boolean announce = true;
    public static boolean root = false;

    public static void buildAdvancement() {
        if (Bukkit.getAdvancement(SkriptAdvancements.getKey(name)) == null) {
            if (root) {
                AdvancementAPI.createRootAdvancement(name, title, description, icon, background, frame, autoUnlock, hidden, toast, announce);
                return;
            }
            AdvancementAPI.createAdvancement(name, title, description, icon, parent, frame, hidden, toast, announce);
        }
    }
}
