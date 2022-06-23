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
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Grant Advancement")
@Description({"Grant an advancement to a player."})
@Examples({"grant all advancements to the player",
        "grant the advancement \"adventure/root\" to player"})
@Since("1.0")

public class EffGrantAdvancement extends Effect {

    static {
        Skript.registerEffect(EffGrantAdvancement.class, "(grant|add|append|give) [[the] advancement[s]] %advancements% to %player%");
    }

    private Expression<Player> player;
    private Expression<Advancement> advancement;

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.advancement = (Expression<Advancement>) expressions[0];
        this.player = (Expression<Player>) expressions[1];
        return true;
    }

    @Override
    protected void execute(@NonNull Event event) {
        Player user = player.getSingle(event);
        for(Advancement goal : advancement.getAll(event)) {
            if (user == null || goal == null) return;
            AdvancementProgress progress = user.getAdvancementProgress(goal);
            for (String criterion : progress.getRemainingCriteria())
                progress.awardCriteria(criterion);
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "grant advancement " + advancement.toString(event, debug) + " to " + player.toString(event, debug);
    }
}
