package me.hotpocket.skriptadvancements.elements.converters;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.jetbrains.annotations.Nullable;

public class AdvancementConverter {

    static {
        Converters.registerConverter(String.class, Advancement.class, new Converter<String, Advancement>() {
            @Nullable
            @Override
            public Advancement convert(String s) {
                if (s.contains(":")) {
                    return Bukkit.getAdvancement(new NamespacedKey(s.split(":")[0], s.split(":")[1]));
                } else {
                    return Bukkit.getAdvancement(NamespacedKey.minecraft(s));
                }
            }
        });
    }
}