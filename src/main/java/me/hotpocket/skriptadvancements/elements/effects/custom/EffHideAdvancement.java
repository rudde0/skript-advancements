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

@Name("Hide Advancement")
@Description({"Makes the last created advancement with the advancement creation section visible or hidden (hidden by default)."})
@Examples({"make last created advancement visible"})
@RequiredPlugins("Paper")
@Since("1.3")

public class EffHideAdvancement extends Effect {

    static {
        if(Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerEffect(EffHideAdvancement.class, "make [the] [last (created|made)] advancement (1¦visible|2¦hidden)");
    }

    private boolean hidden = false;

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if(getParser().isCurrentSection(SecMakeAdvancement.class)) {
            hidden = parser.mark == 2;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void execute(@NonNull Event event) {
        AdvancementHandler.hidden = hidden;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "make last created advancement visible/hidden";
    }
}
