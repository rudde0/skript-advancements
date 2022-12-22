package me.hotpocket.skriptadvancements.utils;

import ch.njol.skript.Skript;
import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import me.hotpocket.skriptadvancements.SkriptAdvancements;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CreationUtils {

    private static UltimateAdvancementAPI api = UltimateAdvancementAPI.getInstance(SkriptAdvancements.getInstance());
    public static AdvancementTab lastCreatedTab;
    public static Advancement lastCreatedAdvancement;
    public static HashMap<AdvancementTab, RootAdvancement> rootAdvancement = new HashMap<>();
    public static HashMap<AdvancementTab, List<BaseAdvancement>> baseAdvancements = new HashMap<>();

    public static void createBaseAdvancement(String name, ItemStack icon, String title, String description, AdvancementFrameType frame, boolean toast, boolean announcement, int x, int y, int maxProgression, String parent) {
        if (lastCreatedTab != null && rootAdvancement.get(lastCreatedTab) != null) {
            Advancement testParent = rootAdvancement.get(lastCreatedTab);
            Advancement realParent = null;
            if ((asString(testParent)).equals(parent)) {
                realParent = rootAdvancement.get(lastCreatedTab);
            } else {
                if (baseAdvancements.containsKey(lastCreatedTab)) {
                    for (Advancement adv : baseAdvancements.get(lastCreatedTab)) {
                        if ((asString(adv)).equals(parent)) {
                            realParent = adv;
                        }
                    }
                } else {
                    return;
                }
            }
            BaseAdvancement advancement;
            if (maxProgression != 0) {
                advancement = new BaseAdvancement(name,
                        new AdvancementDisplay(icon, translate(title), frame, toast, announcement, x, y, translate(description)), realParent, maxProgression);
            } else {
                advancement = new BaseAdvancement(name,
                        new AdvancementDisplay(icon, translate(title), frame, toast, announcement, x, y, translate(description)), realParent);
            }
            lastCreatedAdvancement = advancement;
            List<BaseAdvancement> list = new ArrayList<>();
            list.add(advancement);
            if (baseAdvancements.containsKey(lastCreatedTab)) {
                baseAdvancements.remove(lastCreatedTab);
            }
            baseAdvancements.put(lastCreatedTab, list);
        }
    }

    public static void createRootAdvancement(String name, ItemStack icon, Material background, String title, String description, AdvancementFrameType frame, boolean toast, boolean announcement, int x, int y, int maxProgression) {
        if (lastCreatedTab != null) {
            RootAdvancement advancement;
            if (maxProgression != 0) {
                advancement = new RootAdvancement(lastCreatedTab, name,
                        new AdvancementDisplay(icon, translate(title), frame, toast, announcement, x, y, translate(description)), getTexture(background), maxProgression);
            } else {
                advancement = new RootAdvancement(lastCreatedTab, name,
                        new AdvancementDisplay(icon, translate(title), frame, toast, announcement, x, y, translate(description)), getTexture(background));
            }
            lastCreatedAdvancement = advancement;
            if (rootAdvancement.containsKey(lastCreatedTab)) {
                rootAdvancement.replace(lastCreatedTab, advancement);
            } else {
                rootAdvancement.put(lastCreatedTab, advancement);
            }
        }
    }

    public static void build() {
        if (!lastCreatedTab.isInitialised()) {
            if (rootAdvancement.get(lastCreatedTab) != null && baseAdvancements.get(lastCreatedTab) != null) {
                lastCreatedTab.registerAdvancements(rootAdvancement.get(lastCreatedTab), new HashSet<>(baseAdvancements.get(lastCreatedTab)));
                updateAdvancements();
            } else {
                Skript.error("You must have a root advancement and one or more base advancement with a parent in an advancement tab!");
                RootAdvancement tempRoot = new RootAdvancement(lastCreatedTab, "name", new AdvancementDisplay(Material.DIAMOND, "title", AdvancementFrameType.TASK, false, false, 0, 0, "description"), getTexture(Material.DIAMOND_BLOCK));
                BaseAdvancement tempBase = new BaseAdvancement("name1", new AdvancementDisplay(Material.DIAMOND, "title", AdvancementFrameType.TASK, false, false, 0, 0, "description"), tempRoot);
                lastCreatedTab.registerAdvancements(tempRoot, tempBase);
                CustomUtils.getAPI().unregisterAdvancementTab(lastCreatedTab.getNamespace());
            }
        }
    }

    public static String getTexture(Material block) {
        if (block.isBlock() && block.isSolid())
            return "textures/block/" + Bukkit.getUnsafe().getTranslationKey(block).split("minecraft\\.")[1] + ".png";
        return "texture/block/dirt.png";
    }

    private static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static void updateAdvancements() {
        for (AdvancementTab tab : api.getTabs()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                tab.updateAdvancementsToTeam(player);
            }
        }
    }

    private static String asString(Advancement a) {
        return a.getKey().getNamespace() + "/" + a.getKey().getKey();
    }
}
