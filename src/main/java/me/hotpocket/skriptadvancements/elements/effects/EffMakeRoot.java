package me.hotpocket.skriptadvancements.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Make Root Advancement")
@Description({"Makes the last created advancement a root advancement."})
@Examples({"make advancement a root advancement"})
@Since("1.3")

public class EffMakeRoot extends Effect {

    static {
        Skript.registerEffect(EffMakeRoot.class, "make [the] [last (created|made)] advancement [a] root [advancement]");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if(getParser().isCurrentSection(SecMakeAdvancement.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void execute(@NonNull Event event) {
        AdvancementHandler.makeRoot();
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "make " + AdvancementHandler.lastCreatedAdvancement + " a root advancement";
    }
}
