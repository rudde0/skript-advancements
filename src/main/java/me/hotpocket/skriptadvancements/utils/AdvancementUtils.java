package me.hotpocket.skriptadvancements.utils;

import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;

public class AdvancementUtils {

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
}
