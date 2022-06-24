package me.hotpocket.skriptadvancements.elements;

import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import me.hotpocket.skriptadvancements.Skriptadvancements;
import org.bukkit.Material;

public class AdvancementHandler {

    private static UltimateAdvancementAPI api = UltimateAdvancementAPI.getInstance(Skriptadvancements.getInstance());

    private static String advancementName = null;
    private static Advancement lastCreatedAdvancement;
    private static boolean isRoot = false;
    private static String advancementTitle = "New Advancement Name";
    private static Material advancementIcon = Material.DIRT;
    private static AdvancementFrameType advancementFrame = AdvancementFrameType.TASK;
    private static boolean showToast = true;
    private static boolean announceToChat = true;
    private static int floatX = 0;
    private static int floatY = 0;
    private static Material advancementBackground = Material.DIRT;
    private static String advancementDescription = "New Advancement Description";
    private static int advancementProgression = 0;

    public static void setAdvancementName(String name) {
        advancementName = name;
    }
    public static String getAdvancementName() {
        return advancementName;
    }
    public static String getTabName() {
        return advancementName.split("/")[0];
    }

    public static void setRoot(Boolean root) {
        isRoot = root;
    }
    public static boolean isRoot() {
        return isRoot;
    }

    public static void setAdvancementTitle(String name) {
        advancementTitle = name;
    }
    public static String getAdvancementTitle() {
        return advancementTitle;
    }

    public static String getKeyFromAdvancement(String advancement) {
        return advancement.split(":")[0];
    }

    public static Advancement getLastCreatedAdvancement() {
        return lastCreatedAdvancement;
    }

    public static void setAdvancementIcon(Material icon) {
        advancementIcon = icon;
    }
    public static Material getAdvancementIcon() {
        return advancementIcon;
    }

    public static void setAdvancementFrame(AdvancementFrameType frame) {
        advancementFrame = frame;
    }
    public static AdvancementFrameType getAdvancementFrame() {
        return advancementFrame;
    }

    public static void setShowToast(Boolean show) {
        showToast = show;
    }
    public static boolean getShowToast() {
        return showToast;
    }

    public static void setAnnounceToChat(Boolean announce) {
        announceToChat = announce;
    }
    public static boolean getAnnounceToChat() {
        return announceToChat;
    }

    public static void setBackground(Material background) {
        advancementBackground = background;
    }
    public static Material getBackground() {
        return advancementBackground;
    }

    public static void setDescription(String description) {
        advancementDescription = description;
    }
    public static String getDescription() {
        return advancementDescription;
    }

    public static void setAdvancementProgression(Integer progression) {
        advancementProgression = progression;
    }
    public static int getAdvancementProgression() {
        return advancementProgression;
    }

    public static void buildAdvancement() {
        if(isRoot()) {
            RootAdvancement advancement = new RootAdvancement(api.getAdvancementTab(getTabName()), getTabName(), new AdvancementDisplay(getAdvancementIcon(), getAdvancementTitle(), getAdvancementFrame(), getShowToast(), getAnnounceToChat(), floatX, floatY, getDescription()), "textures/block/" + getBackground().translationKey() + ".png");
            api.createAdvancementTab(getTabName()).registerAdvancements(advancement);
        }
        else {
            BaseAdvancement advancement = new BaseAdvancement(getAdvancementName(), new AdvancementDisplay(getAdvancementIcon(), getAdvancementTitle(), getAdvancementFrame(), getShowToast(), getAnnounceToChat(), 1.5f, 0, getDescription()), api.getAdvancementTab(getTabName()).getRootAdvancement(), getAdvancementProgression());
            api.getAdvancementTab(getTabName()).registerAdvancements(api.getAdvancementTab(getTabName()).getRootAdvancement(), advancement);
        }
        advancementName = null;
        isRoot = false;
        advancementTitle = "New Advancement Name";
        advancementIcon = Material.DIRT;
        advancementFrame = AdvancementFrameType.TASK;
        showToast = true;
        announceToChat = true;
        floatX = 0;
        floatY = 0;
        advancementBackground = Material.DIRT;
        advancementDescription = "New Advancement Description";
        advancementProgression = 0;
    }
}
