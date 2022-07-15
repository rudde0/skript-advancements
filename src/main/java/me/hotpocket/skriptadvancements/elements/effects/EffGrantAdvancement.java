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
import me.hotpocket.skriptadvancements.utils.AdvancementUtils;
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
        Skript.registerEffect(EffGrantAdvancement.class, "(grant|add|append|give) [[the] advancement[s]] %advancements% to %players%");
    }

    private Expression<Player> players;
    private Expression<Advancement> advancements;

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.advancements = (Expression<Advancement>) expressions[0];
        this.players = (Expression<Player>) expressions[1];
        return true;
    }

    @Override
    protected void execute(@NonNull Event event) {
        for(Player player : players.getArray(event)) {
            for (Advancement advancement : advancements.getArray(event)) {
                if (player == null || advancement == null) return;
                AdvancementUtils.grantAdvancement(player, advancement);
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "grant advancement " + advancements.toString(event, debug) + " to " + players.toString(event, debug);
    }
}
