package me.hotpocket.skriptadvancements.elements.conditions.display;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;

public class CondAnnouncesToChat extends Condition {

    static {
        Skript.registerCondition(CondAnnouncesToChat.class,
                "[[the] advancement[s]] %advancements% (does announce|announces) [to [the] chat]",
                "[[the] advancement[s]] %advancements% does(n't| not) announce [to [the] chat]");
    }
    private Expression<Advancement> advancements;

    @Override
    public String toString(Event event, boolean debug) {
        return advancements.toString(event, debug) + " does/doesn't announce to chat";
    }

    @Override
    public boolean check(Event event) {
        return advancements.check(event, advancement -> advancement.getDisplay().doesAnnounceToChat(), isNegated());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) expressions[0];
        setNegated(i == 1);
        return true;
    }
}
