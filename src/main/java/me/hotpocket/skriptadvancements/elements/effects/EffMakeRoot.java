package me.hotpocket.skriptadvancements.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecCreateAdvancement;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public class EffMakeRoot extends Effect {

    static {
        Skript.registerEffect(EffMakeRoot.class, "(make|set) [the] advancement to [a] root [advancement]");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return getParser().isCurrentSection(SecCreateAdvancement.class);
    }

    @Override
    protected void execute(@NonNull Event event) {
        AdvancementHandler.setRoot(true);
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "make advancement a root advancement";
    }
}
