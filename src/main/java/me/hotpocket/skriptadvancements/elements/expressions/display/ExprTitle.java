package me.hotpocket.skriptadvancements.elements.expressions.display;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprTitle extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprTitle.class, String.class, ExpressionType.SIMPLE, "[the] title of [[the] advancement[s]] %advancements%",
                "[[the] advancement[s]] %advancements%'[s] title");
    }

    private Expression<Advancement> advancements;

    @Override
    protected @Nullable String[] get(Event e) {
        for(Advancement advancement : advancements.getArray(e)) {
            return new String[]{ Bukkit.getUnsafe().legacyComponentSerializer().serialize(advancement.getDisplay().title()) };
        }
        return null;
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
        return "title of " + advancements.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) exprs[0];
        return true;
    }
}
