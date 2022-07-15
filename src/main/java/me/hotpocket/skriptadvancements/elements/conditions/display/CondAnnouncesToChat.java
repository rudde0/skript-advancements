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

@Name("Does Announce to Chat")
@Description({"Gets whether or not an advancement announces to the chat."})
@Examples("if \"skript:group/my_advancement\" announces to chat")
@Since("1.3.2")

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
