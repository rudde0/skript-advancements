package me.hotpocket.skriptadvancements.utils;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class CustomAdvancement {

    public static String name;
    public static ItemStack icon = new ItemStack(Material.STICK);
    public static Material background = Material.DIRT;
    public static String title = "My Title";
    public static String description = "My Description";
    public static AdvancementFrameType frame = AdvancementFrameType.TASK;
    public static boolean showToast = true;
    public static boolean announce = true;
    public static boolean root = false;
    public static int row = 0;
    public static int column = 1;
    public static int maxProgression = 0;
    public static String parent = null;

    public static void build() {
        if (CreationUtils.lastCreatedTab != null) {
            if (root) {
                CreationUtils.createRootAdvancement(name, icon, background, title, description, frame, showToast, announce, min(max(row, 0), 9999999), min(max(column, 1), 9999999), maxProgression);
            } else {
                if (parent != null)
                    CreationUtils.createBaseAdvancement(name, icon, title, description, frame, showToast, announce, min(max(row, 0), 9999999), min(max(column, 1), 9999999), maxProgression, parent);
            }
            icon = new ItemStack(Material.STICK);
            background = Material.DIRT;
            title = "My Title";
            description = "My Description";
            frame = AdvancementFrameType.TASK;
            showToast = true;
            announce = true;
            root = false;
            row = 0;
            column = 1;
            maxProgression = 0;
            parent = null;
        }
    }
}
