package me.hotpocket.skriptadvancements.elements.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.fren_gor.ultimateAdvancementAPI.events.advancement.AdvancementRegistrationEvent;
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.jetbrains.annotations.Nullable;

public class EvtAdvancementRegister extends SimpleEvent {
    static {
        Skript.registerEvent("Advancement Register", SimpleEvent.class, AdvancementRegistrationEvent.class, "advancement (register[ed]|create[d])")
                .since("1.4")
                .description("Called when an advancement is created.")
                .examples("on advancement register:");
        EventValues.registerEventValue(AdvancementRegistrationEvent.class, Advancement.class, new Getter<Advancement, AdvancementRegistrationEvent>() {
            @Override
            @Nullable
            public Advancement get(AdvancementRegistrationEvent e) {
                return Bukkit.getAdvancement(e.getAdvancement().getKey().toNamespacedKey());
            }

        }, 0);
    }
}
