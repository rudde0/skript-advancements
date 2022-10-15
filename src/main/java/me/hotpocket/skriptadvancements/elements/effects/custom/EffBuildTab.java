package me.hotpocket.skriptadvancements.elements.effects.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.elements.sections.SecAdvancementTab;
import me.hotpocket.skriptadvancements.utils.CreationUtils;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffBuildTab extends Effect {

    static {
        Skript.registerEffect(EffBuildTab.class, "build [[the] last (created|made)] advancement tab");
    }

    @Override
    protected void execute(Event e) {
        CreationUtils.build();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "build the advancement tab";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return getParser().isCurrentSection(SecAdvancementTab.class);
    }
}
