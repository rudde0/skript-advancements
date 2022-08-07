package me.hotpocket.skriptadvancements.utils;

import de.tr7zw.nbtapi.NBTItem;
import me.hotpocket.skriptadvancements.SkriptAdvancements;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.logging.Level;

public class AdvancementAPI {

    private static String rootJSON = "{\"display\": {\"icon\": {\"item\": \"minecraft:the_icon\",\"nbt\": \"the_nbt\"},\"title\": {\"text\": \"the_title\"}, \"description\": {\"text\": \"the_description\"}, \"background\": \"minecraft:textures/block/the_background.png\", \"frame\": \"the_frame\", \"show_toast\": does_show_toast, \"announce_to_chat\": does_announce, \"hidden\": is_hidden  }, \"criteria\": {\"auto\": {\"trigger\": \"minecraft:is_auto\", \"conditions\": {}}, \"advancement_name\": {\"trigger\": \"minecraft:impossible\", \"conditions\": {}}}}";
    private static String childJSON = "{\"parent\": \"the_parent\", \"display\": {\"icon\": {\"item\": \"minecraft:the_icon\", \"nbt\": \"the_nbt\"}, \"title\": \"the_title\", \"description\": \"the_description\", \"frame\": \"the_frame\", \"hidden\": is_hidden}, \"criteria\": {\"advancement_name\": {\"trigger\": \"minecraft:impossible\", \"conditions\": {}}}}";

    public static void grantAdvancement(Player player, Advancement advancement) {
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        for(String criteria : progress.getRemainingCriteria())
            progress.awardCriteria(criteria);
    }

    public static void revokeAdvancement(Player player, Advancement advancement) {
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        for(String criteria : progress.getAwardedCriteria())
            progress.revokeCriteria(criteria);
    }

    public static void createRootAdvancement(String name, String title, String description, ItemStack icon, Material background, Frame frame, boolean unlock, boolean hidden, boolean toast, boolean announce) {

        String key = "skript";

        if(SkriptAdvancements.getInstance().getConfig().getString("custom-key") != null) {
            key = SkriptAdvancements.getInstance().getConfig().getString("custom-key");
        }

        String advancementJSON = rootJSON.replaceAll("the_title", title)
                .replace("the_description", description)
                .replace("the_icon", icon.getTranslationKey().split("minecraft\\.")[1])
                .replace("the_background", background.getTranslationKey().split("minecraft\\.")[1])
                .replace("the_frame", frame.name().toLowerCase())
                .replace("is_hidden", String.valueOf(hidden))
                .replace("does_show_toast", String.valueOf(toast))
                .replace("advancement_name", name)
                .replace("does_announce", String.valueOf(announce))
                .replace("the_nbt", new NBTItem(icon).getCompound().toString().replace("\"", "\\\""));

        if(unlock) {
            advancementJSON = advancementJSON.replaceAll("is_auto", "location");
        } else {
            advancementJSON = advancementJSON.replaceAll("is_auto", "impossible");
        }

        File file = new File(Bukkit.getWorlds().get(0).getWorldFolder(),
                String.join(File.separator, "data", "advancements", new NamespacedKey(key, name).getNamespace(), new NamespacedKey(key, name).getKey()) + ".json");

        if (!file.exists()) {
            try {
                Bukkit.getUnsafe().loadAdvancement(new NamespacedKey(key, name), advancementJSON);
                if(SkriptAdvancements.getInstance().getConfig().getConfigurationSection("reload").getBoolean("on-create")) {
                    Bukkit.reloadData();
                }
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.SEVERE, "Error occurred while creating the advancement '" + new NamespacedKey(key, name) + "'!", e);
            }
        }
    }

    public static void createAdvancement(String name, String title, String description, ItemStack icon, NamespacedKey parent, Frame frame, boolean hidden, boolean toast, boolean announce) {

        String key = "skript";

        if(SkriptAdvancements.getInstance().getConfig().getString("custom-key") != null) {
            key = SkriptAdvancements.getInstance().getConfig().getString("custom-key");
        }

        String advancementJSON = childJSON.replaceAll("the_title", title)
                .replace("the_description", description)
                .replace("the_icon", icon.getTranslationKey().split("minecraft\\.")[1])
                .replace("the_frame", frame.name().toLowerCase())
                .replace("is_hidden", String.valueOf(hidden))
                .replace("does_show_toast", String.valueOf(toast))
                .replace("advancement_name", name)
                .replace("does_announce", String.valueOf(announce))
                .replace("the_parent", parent.getNamespace() + ":" + parent.getKey())
                .replace("the_nbt", new NBTItem(icon).getCompound().toString().replace("\"", "\\\""));

        File file = new File(Bukkit.getWorlds().get(0).getWorldFolder(),
                String.join(File.separator, "data", "advancements", new NamespacedKey(key, name).getNamespace(), new NamespacedKey(key, name).getKey()) + ".json");

        if (!file.exists()) {
            try {
                Bukkit.getUnsafe().loadAdvancement(new NamespacedKey(key, name), advancementJSON);
                if(SkriptAdvancements.getInstance().getConfig().getConfigurationSection("reload").getBoolean("on-create")) {
                    Bukkit.reloadData();
                }
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.SEVERE, "Error occurred while creating the advancement '" + new NamespacedKey(key, name) + "'!", e);
            }
        }
    }

    public enum Frame {
        TASK,
        CHALLENGE,
        GOAL
    }
}
