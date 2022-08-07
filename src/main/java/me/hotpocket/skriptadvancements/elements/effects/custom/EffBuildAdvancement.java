package me.hotpocket.skriptadvancements.elements.effects.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Build Advancement")
@Description({"Builds the last created advancement made with the advancement creation section."})
@Examples({"build the last created advancement"})
@RequiredPlugins("Paper")
@Since("1.3")

public class EffBuildAdvancement extends Effect {

    static {
        if (Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerEffect(EffBuildAdvancement.class, "(build|finish|make|complete|activate) [the] [last (created|made)] advancement");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        return getParser().isCurrentSection(SecMakeAdvancement.class);
    }

    @Override
    protected void execute(@NonNull Event event) {
        AdvancementHandler.buildAdvancement();
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "build the last created advancement";
    }
}
