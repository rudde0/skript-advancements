package me.hotpocket.skriptadvancements.utils;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import me.hotpocket.skriptadvancements.SkriptAdvancements;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CustomUtils {

    private static UltimateAdvancementAPI api = UltimateAdvancementAPI.getInstance(SkriptAdvancements.getInstance());

    public static List<Advancement> getPlayerAdvancements(Player player) {
        List<Advancement> advancementList = new ArrayList<>();
        for (Advancement advancement : getAdvancements()) {
            if (advancement.isGranted(player)) {
                advancementList.add(advancement);
            }
        }
        return advancementList;
    }

    public static List<Advancement> getAdvancements() {
        List<Advancement> advancementList = new ArrayList<>();
        for (AdvancementTab tab : api.getTabs()) {
            if (tab.isInitialised())
                advancementList.addAll(tab.getAdvancements());
        }
        return advancementList;
    }

    public static void setPlayerAdvancements(List<Advancement> advancementList, Player player) {
        for (Advancement advancement : getPlayerAdvancements(player)) {
            advancement.revoke(player);
        }
        for (Advancement advancement : advancementList) {
            advancement.grant(player);
        }
    }

    public static UltimateAdvancementAPI getAPI() {
        return api;
    }

    public static boolean exists(String tab, String name) {
        return api.getAdvancement(tab, name) != null;
    }
}
