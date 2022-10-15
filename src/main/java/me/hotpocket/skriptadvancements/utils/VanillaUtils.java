package me.hotpocket.skriptadvancements.utils;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VanillaUtils {

    public static void grantAdvancement(Player player, Advancement advancement) {
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        for (String criteria : progress.getRemainingCriteria())
            progress.awardCriteria(criteria);
    }

    public static void revokeAdvancement(Player player, Advancement advancement) {
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        for (String criteria : progress.getAwardedCriteria())
            progress.revokeCriteria(criteria);
    }

    public static List<Advancement> getAdvancements() {
        List<Advancement> advancementList = new ArrayList<>();
        Iterator<Advancement> iterator = Bukkit.getServer().advancementIterator();
        while (iterator.hasNext()) {
            advancementList.add(iterator.next());
        }
        return advancementList;
    }

    public static List<Advancement> getPlayerAdvancements(Player player) {
        List<Advancement> advancementList = new ArrayList<>();
        for (Advancement advancement : getAdvancements()) {
            if (player.getAdvancementProgress(advancement).isDone()) {
                advancementList.add(advancement);
            }
        }
        return advancementList;
    }

    public static void setPlayerAdvancements(List<Advancement> advancementList, Player player) {
        for (Advancement advancement : getPlayerAdvancements(player)) {
            revokeAdvancement(player, advancement);
        }
        for (Advancement advancement : advancementList) {
            grantAdvancement(player, advancement);
        }
    }
}
