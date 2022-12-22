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
import org.jetbrains.annotations.Nullable;

@Name("Creation - Advancement Parent")
@Description({"Sets the parent of a custom advancement to any custom advancement represented as a string.",
"Remember to NOT use the custom advancement expression, use a string instead!"})
@Examples("set parent of advancement to \"tabName/advancementName\"")
@Since("1.4")

public class ExprAdvancementParent extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAdvancementParent.class, String.class, ExpressionType.SIMPLE,
                "[the] parent of [the] [last (created|made)] [custom] advancement",
                "[the] [last (created|made)] [custom] advancement[']s parent");
    }

    @Override
    protected @Nullable String[] get(Event e) {
        return new String[]{CustomAdvancement.parent};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the parent of the advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return switch (mode) {
            case SET -> CollectionUtils.array(String.class);
            default -> null;
        };
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        assert delta[0] != null;
        String namespace = ((String) delta[0]).split("/")[0];
        String key = ((String) delta[0]).split("/")[1];
        CustomAdvancement.parent = namespace.toLowerCase().replaceAll(" ", "_") + "/" + key;
    }
}
