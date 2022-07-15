package me.hotpocket.skriptadvancements.elements.conditions.display;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;

@Name("Does Show Toast")
@Description({"Gets whether or not an advancement shows toast or not."})
@Examples("if \"adventure/root\" shows toast")
@Since("1.3.2")

public class CondShowsToast extends Condition {

    static {
        Skript.registerCondition(CondShowsToast.class,
                "[[the] advancement[s]] %advancements% (does show|shows) toast",
                "[[the] advancement[s]] %advancements% does(n't| not) show toast");
    }
    private Expression<Advancement> advancements;

    @Override
    public String toString(Event event, boolean debug) {
        return advancements.toString(event, debug) + " does/doesn't show toast";
    }

    @Override
    public boolean check(Event event) {
        return advancements.check(event, advancement -> advancement.getDisplay().doesShowToast(), isNegated());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) expressions[0];
        setNegated(i == 1);
        return true;
    }
}
