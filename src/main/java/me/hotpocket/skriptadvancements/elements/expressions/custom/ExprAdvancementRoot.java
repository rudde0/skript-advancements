package me.hotpocket.skriptadvancements.elements.expressions.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.hotpocket.skriptadvancements.utils.CustomAdvancement;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprAdvancementRoot extends SimpleExpression<Boolean> {

    static {
        Skript.registerExpression(ExprAdvancementRoot.class, Boolean.class, ExpressionType.SIMPLE,
                "[the] root of [the] [last (created|made)] [custom] advancement",
                "[the] [last (created|made)] [custom] advancement[']s root");
    }

    @Override
    protected @Nullable Boolean[] get(Event e) {
        return new Boolean[]{CustomAdvancement.root};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the root of the advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return switch (mode) {
            case SET -> CollectionUtils.array(Boolean.class);
            default -> null;
        };
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        assert delta[0] != null;
        CustomAdvancement.root = (Boolean) delta[0];
    }
}
