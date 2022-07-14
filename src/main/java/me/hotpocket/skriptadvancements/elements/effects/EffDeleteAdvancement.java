package me.hotpocket.skriptadvancements.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffDeleteAdvancement extends Effect {

    static {
        Skript.registerEffect(EffDeleteAdvancement.class, "delete [[the] advancement[s]] %advancements%");
    }

    private Expression<Advancement> advancements;

    @Override
    protected void execute(Event e) {
        for(Advancement advancement : advancements.getArray(e)) {
            for(Advancement child : advancement.getChildren()) {
                Bukkit.getUnsafe().removeAdvancement(child.getKey());
            }
            Bukkit.getUnsafe().removeAdvancement(advancement.getKey());
        }
        Bukkit.reloadData();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "delete the advancement " + advancements.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) exprs[0];
        return true;
    }
}
