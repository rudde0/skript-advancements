package me.hotpocket.skriptadvancements.utils;

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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CreationUtils {

    private static UltimateAdvancementAPI api = UltimateAdvancementAPI.getInstance(SkriptAdvancements.getInstance());
    public static AdvancementTab lastCreatedTab;
    public static Advancement lastCreatedAdvancement;
    public static HashMap<AdvancementTab, RootAdvancement> rootAdvancement = new HashMap<>();
    public static HashMap<AdvancementTab, Set<BaseAdvancement>> baseAdvancements = new HashMap<>();

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
            Set<BaseAdvancement> set = new HashSet<>();
            set.add(advancement);
            if (baseAdvancements.containsKey(lastCreatedTab)) {
                set.addAll(baseAdvancements.get(lastCreatedTab));
                baseAdvancements.replace(lastCreatedTab, set);
            } else {
                baseAdvancements.put(lastCreatedTab, set);
            }
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
            Set<BaseAdvancement> base = new HashSet<>();
            if (baseAdvancements.containsKey(lastCreatedTab))
                base.addAll(baseAdvancements.get(lastCreatedTab));
            lastCreatedTab.registerAdvancements(rootAdvancement.get(lastCreatedTab), base);
            updateAdvancements();
        }
    }

    public static String getTexture(Material block) {
        if (block.isBlock() && block.isSolid())
            return "textures/block/" + block.translationKey().split("minecraft\\.")[1] + ".png";
        return "texture/block/dirt.png";
    }

    private static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static void updateAdvancements() {
        for (AdvancementTab tab : api.getTabs()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                tab.updateEveryAdvancement(player);
            }
        }
    }

    private static String asString(Advancement a) {
        return a.getKey().getNamespace() + "/" + a.getKey().getKey();
    }

    public static void disposeTab() {
        if (lastCreatedTab != null) {
            if (lastCreatedTab.isInitialised()) {
                Set<BaseAdvancement> base = new HashSet<>();
                RootAdvancement root = null;

                try {
                    rootAdvancement.remove(lastCreatedTab);
                    baseAdvancements.remove(lastCreatedTab);
                    Field f = AdvancementTab.class.getDeclaredField("initialised");
                    f.setAccessible(true);
                    f.set(lastCreatedTab, false);
                    f = AdvancementTab.class.getDeclaredField("rootAdvancement");
                    f.setAccessible(true);
                    f.set(lastCreatedTab, null);
                    f = AdvancementTab.class.getDeclaredField("advsWithoutRoot");
                    f.setAccessible(true);
                    f.set(lastCreatedTab, null);
                    f = Advancement.class.getDeclaredField("advancementTab");
                    f.setAccessible(true);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
