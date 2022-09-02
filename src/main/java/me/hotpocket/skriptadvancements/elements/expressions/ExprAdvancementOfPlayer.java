package me.hotpocket.skriptadvancements.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.utils.AdvancementAPI;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancements From Player")
@Description({"Get all of the advancements from a player."})
@Examples("all advancements from player")
@Since("1.3")

public class ExprAdvancementOfPlayer extends SimpleExpression<Advancement> {

    static {
        Skript.registerExpression(ExprAllAdvancements.class, Advancement.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] advancements (of|from) %players%");
    }

    private Expression<Player> players;

    @Override
    public Class<? extends Advancement> getReturnType() {
        return Advancement.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        players = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a list of advancements from " + players.toString(event, debug);
    }

    @Override
    @Nullable
    protected Advancement[] get(Event event) {
        for (Player player : players.getArray(event)) {
            return AdvancementAPI.getAdvancementsOfPlayer(player).toArray(new Advancement[AdvancementAPI.getAllAdvancements().size()]);
        }
        return null;
    }
}
