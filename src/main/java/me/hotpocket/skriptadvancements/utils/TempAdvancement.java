package me.hotpocket.skriptadvancements.utils;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TempAdvancement {

    private static String name;
    private static String tab;
    private static String parent;
    private static AdvancementDisplay display;
    private static Integer maxProgression;
    private static Boolean root;
    private static Material background;


    public TempAdvancement(String name, String tab, AdvancementDisplay display, String parent, int maxProgression, boolean root, Material background) {
        TempAdvancement.name = name;
        TempAdvancement.tab = tab;
        TempAdvancement.parent = parent;
        TempAdvancement.display = display;
        TempAdvancement.maxProgression = maxProgression;
        TempAdvancement.root = root;
        TempAdvancement.background = background;
    }

    public String getName() {
        return name;
    }

    public String getTab() {
        return tab;
    }

    public void setParent(String parent) {
        TempAdvancement.parent = parent;
    }

    public String getParent() {
        return parent;
    }

    public AdvancementDisplay getDisplay() {
        return display;
    }

    public int getMaxProgression() {
        return maxProgression;
    }

    public boolean getRoot() {
        return root;
    }

    public Material getBackground() {
        return background;
    }

    public void setIcon(ItemStack icon) {
        display = new AdvancementDisplay(icon, display.getTitle(), display.getFrame(), display.doesShowToast(), display.doesAnnounceToChat(), display.getX(), display.getY(), display.getDescription());
    }

    public void setTitle(String title) {
        display = new AdvancementDisplay(display.getIcon(), translate(title), display.getFrame(), display.doesShowToast(), display.doesAnnounceToChat(), display.getX(), display.getY(), display.getDescription());
    }

    public void setFrame(AdvancementFrameType frame) {
        display = new AdvancementDisplay(display.getIcon(), display.getTitle(), frame, display.doesShowToast(), display.doesAnnounceToChat(), display.getX(), display.getY(), display.getDescription());
    }

    public void setToast(boolean toast) {
        display = new AdvancementDisplay(display.getIcon(), display.getTitle(), display.getFrame(), toast, display.doesAnnounceToChat(), display.getX(), display.getY(), display.getDescription());
    }

    public void setAnnounce(boolean announce) {
        display = new AdvancementDisplay(display.getIcon(), display.getTitle(), display.getFrame(), display.doesShowToast(), announce, display.getX(), display.getY(), display.getDescription());
    }

    public void setX(int x) {
        display = new AdvancementDisplay(display.getIcon(), display.getTitle(), display.getFrame(), display.doesShowToast(), display.doesAnnounceToChat(), x, display.getY(), display.getDescription());
    }

    public void setY(int y) {
        display = new AdvancementDisplay(display.getIcon(), display.getTitle(), display.getFrame(), display.doesShowToast(), display.doesAnnounceToChat(), display.getX(), y, display.getDescription());
    }

    public void setDescription(List<String> description) {
        display = new AdvancementDisplay(display.getIcon(), display.getTitle(), display.getFrame(), display.doesShowToast(), display.doesAnnounceToChat(), display.getX(), display.getY(), description);
    }

    public void setMaxProgression(int progression) {
        maxProgression = progression;
    }

    public void setRoot(boolean root) {
        TempAdvancement.root = root;
    }

    public void setBackground(Material background) {
        TempAdvancement.background = background;
    }

    String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String getTexture(Material block) {
        if (block.isBlock() && block.isSolid())
            return "textures/block/" + Bukkit.getUnsafe().getTranslationKey(block).split("minecraft\\.")[1] + ".png";
        return "texture/block/dirt.png";
    }

    static String asString(Advancement a) {
        return a.getKey().getNamespace() + "/" + a.getKey().getKey();
    }

    public static Advancement fromString(String advancement) {
        if (Creator.advancements.get(tab) != null) {
            for (Advancement adv : Creator.advancements.get(tab)) {
                if (asString(adv).equals(advancement)) {
                    return adv;
                }
            }
        }
        return null;
    }

    public void build() {
        if (Creator.lastCreatedTab.equals(tab)) {
            Advancement advancement = null;
            if (root) {
                if (maxProgression > 0) {
                    advancement = new RootAdvancement(CustomUtils.getAPI().getAdvancementTab(tab), name, display, getTexture(background), maxProgression);
                } else {
                    advancement = new RootAdvancement(CustomUtils.getAPI().getAdvancementTab(tab), name, display, getTexture(background));
                }
            } else {
                if (fromString(parent) != null) {
                    if (maxProgression > 0) {
                        advancement = new BaseAdvancement(name, display, fromString(parent), maxProgression);
                    } else {
                        advancement = new BaseAdvancement(name, display, fromString(parent));
                    }
                }
            }
            if (advancement != null) {
                List<Advancement> advancementList = new ArrayList<>();
                if (Creator.advancements.containsKey(tab)) {
                    Creator.advancements.get(tab).add(advancement);
                } else {
                    advancementList.add(advancement);
                    Creator.advancements.put(tab, advancementList);
                }
                Creator.tempAdvancements.remove(this);
            }
        }
    }
}
