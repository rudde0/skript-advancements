package me.hotpocket.skriptadvancements.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.hotpocket.skriptadvancements.utils.VanillaUtils;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.List;

public class ExprPlayersVanillaAdvancements extends SimpleExpression<Advancement> {

    static {
        Skript.registerExpression(ExprPlayersVanillaAdvancements.class, Advancement.class, ExpressionType.SIMPLE,
                "[all [[of] the]] [vanilla] advancements of %players%",
                "[all [[of] the]] %players%'[s] [vanilla] advancements");
    }

    private Expression<Player> players;

    @Override
    protected @Nullable Advancement[] get(Event e) {
        for (Player player : players.getAll(e))
            return VanillaUtils.getPlayerAdvancements(player).toArray(new Advancement[VanillaUtils.getPlayerAdvancements(player).size()]);
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Advancement> getReturnType() {
        return Advancement.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the vanilla advancements of " + players.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        players = (Expression<Player>) exprs[0];
        return true;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return switch (mode) {
            case SET, ADD, REMOVE, RESET, DELETE -> CollectionUtils.array(Advancement.class);
            default -> null;
        };
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        assert delta[0] != null;
        for (Player player : players.getAll(e)) {
            List<Advancement> playerAdvancements = VanillaUtils.getPlayerAdvancements(player);
            switch (mode) {
                case SET:
                    playerAdvancements.clear();
                    playerAdvancements.add((Advancement) delta[0]);
                    break;
                case ADD:
                    playerAdvancements.add((Advancement) delta[0]);
                    break;
                case REMOVE:
                    playerAdvancements.remove((Advancement) delta[0]);
                    break;
                case RESET, DELETE:
                    playerAdvancements.clear();
                    break;
                default:
                    break;
            }
            VanillaUtils.setPlayerAdvancements(playerAdvancements, player);
        }
    }
}
