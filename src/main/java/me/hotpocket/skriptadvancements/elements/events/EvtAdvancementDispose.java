package me.hotpocket.skriptadvancements.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.fren_gor.ultimateAdvancementAPI.events.advancement.AdvancementDisposeEvent;
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.jetbrains.annotations.Nullable;

public class EvtAdvancementDispose extends SimpleEvent {
    static {
        Skript.registerEvent("Advancement Register", SimpleEvent.class, AdvancementDisposeEvent.class, "advancement (dispose[d]|delete[d])")
                .since("1.4")
                .description("Called shortly before an advancement is disposed.")
                .examples("on advancement dispose:");
        EventValues.registerEventValue(AdvancementDisposeEvent.class, Advancement.class, new Getter<Advancement, AdvancementDisposeEvent>() {
            @Override
            @Nullable
            public Advancement get(AdvancementDisposeEvent e) {
                return Bukkit.getAdvancement(e.getAdvancement().getKey().toNamespacedKey());
            }

        }, 0);
    }
}
