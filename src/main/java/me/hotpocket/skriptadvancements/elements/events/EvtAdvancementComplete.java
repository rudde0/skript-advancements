package me.hotpocket.skriptadvancements.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.jetbrains.annotations.Nullable;

public class EvtAdvancementComplete extends SimpleEvent {
    static {
        Skript.registerEvent("Advancement Complete", SimpleEvent.class, PlayerAdvancementDoneEvent.class, "advancement [(done|complete[d]|grant[ed])]")
                .since("1.0")
                .description("Called when an advancement is completed by a player.")
                .examples("on advancement:");
        EventValues.registerEventValue(PlayerAdvancementDoneEvent.class, Advancement.class, new Getter<Advancement, PlayerAdvancementDoneEvent>() {
            @Override
            @Nullable
            public Advancement get(PlayerAdvancementDoneEvent e) {
                return e.getAdvancement();
            }

        }, 0);
        EventValues.registerEventValue(PlayerAdvancementDoneEvent.class, Player.class, new Getter<Player, PlayerAdvancementDoneEvent>() {
            @Override
            @Nullable
            public Player get(PlayerAdvancementDoneEvent e) {
                return e.getPlayer();
            }

        }, 0);
    }
}