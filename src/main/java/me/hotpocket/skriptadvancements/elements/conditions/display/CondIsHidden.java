package me.hotpocket.skriptadvancements.elements.conditions.display;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;

public class CondIsHidden extends Condition {

    static {
        Skript.registerCondition(CondIsHidden.class,
                "[[the] advancement[s]] %advancements% (is|are) hidden",
                "[[the] advancement[s]] %advancements% (is(n't| not)|are(n't| not)) hidden");
    }
    private Expression<Advancement> advancements;

    @Override
    public String toString(Event event, boolean debug) {
        return advancements.toString(event, debug) + " is/isn't hidden";
    }

    @Override
    public boolean check(Event event) {
        return advancements.check(event, advancement -> advancement.getDisplay().isHidden(), isNegated());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) expressions[0];
        setNegated(i == 1);
        return true;
    }
}
