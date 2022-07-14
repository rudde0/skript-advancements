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
import ch.njol.skript.util.Date;
import ch.njol.util.Kleenean;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Remaining Criteria")
@Description({"This expression allows you to get the remaining criteria of any advancement for a player.",
        "Returns nothing if the advancement is already completed by the player.",
        "If an advancement has not been completed, the remaining criteria of a custom advancement made with skript-advancements will always be 'impossible'."})
@Examples("broadcast \"%remaining criteria of \"adventure/very_very_frightening\" for player%\"")
@Since("1.3.2")
public class ExprRemainingCriteria extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprRemainingCriteria.class, Object.class, ExpressionType.SIMPLE, "[the] remaining criteria of [[the] advancement[s]] %advancements% for %players%",
                "[[the] advancement[s]] %advancements%'[s] remaining criteria for %players%");
    }

    private Expression<Advancement> advancements;
    private Expression<Player> players;

    @Override
    protected @Nullable Object[] get(Event e) {
        for (Player player : players.getArray(e)) {
            for (Advancement advancement : advancements.getArray(e)) {
                return player.getAdvancementProgress(advancement).getRemainingCriteria().toArray();
            }
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Date> getReturnType() {
        return Date.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "remaining criteria of " + advancements.toString(e, debug) + " for " + players.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) exprs[0];
        players = (Expression<Player>) exprs[1];
        return true;
    }
}
