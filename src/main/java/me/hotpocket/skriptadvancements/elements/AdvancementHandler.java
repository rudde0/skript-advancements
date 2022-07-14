package me.hotpocket.skriptadvancements.elements;

import me.hotpocket.skriptadvancements.SkriptAdvancements;
import me.hotpocket.skriptadvancements.advancementcreator.Advancement;
import me.hotpocket.skriptadvancements.advancementcreator.shared.ItemObject;
import me.hotpocket.skriptadvancements.advancementcreator.trigger.ImpossibleTrigger;
import me.hotpocket.skriptadvancements.advancementcreator.trigger.Trigger;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class AdvancementHandler {

    public static Advancement lastCreatedAdvancement;
    private static String advancementTitle = "Default Title";
    private static String advancementDescription = "Default Description";
    private static Material advancementIcon = Material.DIRT;
    private static String advancementName = "default";
    private static String advancementBackground = "block/dirt";
    public  static Boolean advancementRoot = false;
    private static Boolean autoUnlock = false;
    private static Trigger trigger = new ImpossibleTrigger();
    private static Advancement.Frame frame = Advancement.Frame.TASK;
    private static NamespacedKey parent = null;
    private static Boolean hidden = true;

    private static String namespace = SkriptAdvancements.getInstance().getConfig().getString("custom-key");

    public static void setTitle(String title) {
        advancementTitle = title;
        lastCreatedAdvancement = new Advancement(new NamespacedKey(namespace, getName()), new ItemObject().setItem(getIcon()),
                new TextComponent(title), new TextComponent(getDescription()));
    }

    public static String getTitle() {
        return advancementTitle;
    }

    public static void setDescription(String description) {
        advancementDescription = description;
        lastCreatedAdvancement = new Advancement(new NamespacedKey(namespace, getName()), new ItemObject().setItem(getIcon()),
                new TextComponent(getTitle()), new TextComponent(description));
    }

    public static String getDescription() {
        return advancementDescription;
    }

    public static void setIcon(Material icon) {
        advancementIcon = icon;
        lastCreatedAdvancement = new Advancement(new NamespacedKey(namespace, getName()), new ItemObject().setItem(icon),
                new TextComponent(getTitle()), new TextComponent(getDescription()));
    }

    public static Material getIcon() {
        return advancementIcon;
    }

    public static void setName(String name) {
        advancementName = name;
        lastCreatedAdvancement = new Advancement(new NamespacedKey(namespace, name), new ItemObject().setItem(getIcon()),
                new TextComponent(getTitle()), new TextComponent(getDescription()));
    }

    public static String getName() {
        return advancementName;
    }

    public static void setBackground(Material background) {
        advancementBackground = "block/" + background.translationKey().split("minecraft.")[1];
    }

    public static Material getBackground() {
        return Material.valueOf(advancementBackground.split("block/")[1].toUpperCase());
    }

    public static void makeRoot() {
        advancementRoot = !advancementRoot;
    }

    public static Boolean isRoot() {
        return advancementRoot;
    }

    public static void autoUnlock(Boolean bool) {
        autoUnlock = bool;
    }

    public static Trigger getTrigger() {
        return trigger;
    }

    public static void setTrigger(Trigger trig) {
        trigger = trig;
    }

    public static Boolean isAutoUnlock() {
        return autoUnlock;
    }

    public static Advancement.Frame getFrame() {
        return frame;
    }

    public static void setFrame(Advancement.Frame fr) {
        frame = fr;
    }

    public static NamespacedKey getParent() { return parent; }

    public static void setParent(org.bukkit.advancement.Advancement p) { parent = p.getKey(); }

    public static Boolean isHidden() { return hidden; }

    public static void setHidden(Boolean b) { hidden = b; }

    public static void buildAdvancement() {
        if(isRoot()) {
            lastCreatedAdvancement = new Advancement(new NamespacedKey(namespace, getName().replaceAll(":", "")), new ItemObject().setItem(getIcon()),
                    new TextComponent(getTitle()), new TextComponent(getDescription()))
                    .addTrigger(getName(), new ImpossibleTrigger())
                    .setFrame(frame);
            lastCreatedAdvancement.makeRoot("block/" + getBackground().translationKey().split("minecraft.")[1], isAutoUnlock());
        } else {
            lastCreatedAdvancement = new Advancement(new NamespacedKey(namespace, getName()), new ItemObject().setItem(getIcon()),
                    new TextComponent(getTitle()), new TextComponent(getDescription()))
                    .addTrigger(getName(), new ImpossibleTrigger())
                    .setFrame(frame);
            if(parent != null) {
                lastCreatedAdvancement.makeChild(parent);
            }
        }
        lastCreatedAdvancement.setHidden(hidden);
        lastCreatedAdvancement.activate(false);
        advancementTitle = "Default Title";
        advancementDescription = "Default Description";
        advancementIcon = Material.DIRT;
        advancementName = "default";
        advancementBackground = "block/dirt";
        advancementRoot = false;
        autoUnlock = false;
        frame = Advancement.Frame.TASK;
        parent = null;
        hidden = true;
    }
}
