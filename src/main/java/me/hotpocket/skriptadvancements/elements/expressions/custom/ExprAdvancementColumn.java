package me.hotpocket.skriptadvancements.elements.expressions.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.hotpocket.skriptadvancements.utils.CustomAdvancement;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Creation - Advancement Column")
@Description("Sets the column of a custom advancement to an integer.")
@Examples("set column of advancement to 5")
@Since("1.4")

public class ExprAdvancementColumn extends SimpleExpression<Integer> {

    static {
        Skript.registerExpression(ExprAdvancementColumn.class, Integer.class, ExpressionType.SIMPLE,
                "[the] column of [the] [last (created|made)] [custom] advancement",
                "[the] [last (created|made)] [custom] advancement[']s column");
    }

    @Override
    protected @Nullable Integer[] get(Event e) {
        return new Integer[]{CustomAdvancement.column};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Integer> getReturnType() {
        return Integer.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the column of the advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return switch (mode) {
            case SET-> CollectionUtils.array(Number.class);
            default -> null;
        };
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        assert delta[0] != null;
        CustomAdvancement.column = ((Number) delta[0]).intValue();
    }
}
