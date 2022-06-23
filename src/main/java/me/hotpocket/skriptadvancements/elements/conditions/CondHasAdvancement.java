package me.hotpocket.skriptadvancements.elements.conditions;

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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

@Name("Advancements - Has Advancement")
@Description({"Checks whether a player has an advancement or not."})
@Examples({"player has all advancements",
        "player has the advancement \"adventure/root\""})
@Since("1.0")

public class CondHasAdvancement extends Condition {

    static {
        Skript.registerCondition(CondHasAdvancement.class,
                "%players% (has|have) [[the] advancement[s]] %advancements%",
                "%players% (doesn't|does not|do not|don't) have [[the] advancement[s]] %advancements%");
    }

    private Expression<Player> players;
    private Expression<Advancement> advancements;

    @Override
    public String toString(Event event, boolean debug) {
        return "player(s) " + players.toString(event, debug) + " has/doesn't have the advancement(s) " + advancements.toString(event, debug);
    }

    @Override
    public boolean check(Event event) {
        Advancement[] advancements = this.advancements.getArray(event);
        return players.check(event, player -> {
            for (Advancement advancement : advancements)
                return player.getAdvancementProgress(advancement).isDone();
            return false;
        }, isNegated());
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) expressions[0];
        advancements = (Expression<Advancement>) expressions[1];
        setNegated(i == 1);
        return true;
    }
}