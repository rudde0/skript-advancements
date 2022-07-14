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
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Criteria")
@Description({"This expression allows you to get the criteria of any advancement.",
"Criteria of custom advancements made with skript-advancements will always be the advancement."})
@Examples("broadcast \"%criteria of \"adventure/very_very_frightening\"%\"")
@Since("1.3.2")
public class ExprCriteria extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprCriteria.class, Object.class, ExpressionType.SIMPLE, "[the] criteria of [[the] advancement[s]] %advancements%",
                "[[the] advancement[s]] %advancements%'[s] criteria");
    }

    private Expression<Advancement> advancements;

    @Override
    protected @Nullable Object[] get(Event e) {
        for(Advancement advancement : advancements.getArray(e)) {
            return advancement.getCriteria().toArray();
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "criteria of " + advancements.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) exprs[0];
        return true;
    }
}
