package me.hotpocket.skriptadvancements.utils;

import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
    public static int column = 0;
    public static int maxProgression = 0;
    public static String parent = null;

    public static void build() {
        if (CreationUtils.lastCreatedTab != null) {
            if (root) {
                CreationUtils.createRootAdvancement(name, icon, background, title, description, frame, showToast, announce, row, column, maxProgression);
            } else {
                if (parent != null)
                    CreationUtils.createBaseAdvancement(name, icon, title, description, frame, showToast, announce, row, column, maxProgression, parent);
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
            column = 0;
            maxProgression = 0;
            parent = null;
        }
    }
}
