package me.hotpocket.skriptadvancements.utils;

import ch.njol.skript.Skript;
import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.max;
import static java.lang.Math.min;
// min(max(row, 0), 9999999), min(max(column, 1), 9999999)
public class Creator {

    public static List<TempAdvancement> tempAdvancements = new ArrayList<>();
    public static HashMap<String, List<Advancement>> advancements = new HashMap<>();
    public static String lastCreatedTab;
    public static TempAdvancement lastCreatedAdvancement;

    public static void build() {
        if (CustomUtils.getAPI().getAdvancementTab(lastCreatedTab) != null && CustomUtils.getAPI().getAdvancementTab(lastCreatedTab).isInitialised()) {
            CustomUtils.getAPI().unregisterAdvancementTab(lastCreatedTab);
        }
        RootAdvancement root = new RootAdvancement(CustomUtils.getAPI().getAdvancementTab(lastCreatedTab), "temp_root_advancement_name_1289587", new AdvancementDisplay(Material.DIAMOND, "title", AdvancementFrameType.TASK, false, false, 0, 0, "description"), TempAdvancement.getTexture(Material.DIAMOND_BLOCK));
        Set<BaseAdvancement> baseAdvancements = new HashSet<>();
        if (advancements.get(lastCreatedTab) != null) {
            for (Advancement advancement : advancements.get(lastCreatedTab)) {
                if (advancement instanceof RootAdvancement rootAdvancement) {
                    root = rootAdvancement;
                } else if (advancement instanceof BaseAdvancement baseAdvancement) {
                    baseAdvancements.add(baseAdvancement);
                }
            }
        }
        if (root.getKey().equals("temp_root_advancement_name_1289587") || baseAdvancements.size() < 1) {
            BaseAdvancement tempBase = new BaseAdvancement("name1", new AdvancementDisplay(Material.DIAMOND, "title", AdvancementFrameType.TASK, false, false, 0, 0, "description"), root);
            CustomUtils.getAPI().getAdvancementTab(lastCreatedTab).registerAdvancements(root, tempBase);
            CustomUtils.getAPI().unregisterAdvancementTab(lastCreatedTab);
            Skript.error("You need at least one root advancement and a base advancement with a parent to register an advancement tab.");
        } else {
            CustomUtils.getAPI().getAdvancementTab(lastCreatedTab).registerAdvancements(root, baseAdvancements);
            for (AdvancementTab tab : CustomUtils.getAPI().getTabs()) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    tab.updateAdvancementsToTeam(player);
                }
            }
        }
        advancements.remove(lastCreatedTab);
    }
}
