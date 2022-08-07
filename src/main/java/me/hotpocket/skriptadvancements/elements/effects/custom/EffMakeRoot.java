package me.hotpocket.skriptadvancements.elements.effects.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Make Root Advancement")
@Description({"Makes the last created advancement a root advancement."})
@Examples({"make advancement a root advancement"})
@RequiredPlugins("Paper")
@Since("1.3")

public class EffMakeRoot extends Effect {

    static {
        if(Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerEffect(EffMakeRoot.class, "make [the] [last (created|made)] advancement [a] root [advancement]");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return getParser().isCurrentSection(SecMakeAdvancement.class);
    }

    @Override
    protected void execute(@NonNull Event event) {
        AdvancementHandler.root = true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "make last created advancement a root advancement";
    }
}
