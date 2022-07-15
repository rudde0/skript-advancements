package me.hotpocket.skriptadvancements.elements.expressions.display;

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
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Icon")
@Description({"Allows you to get the icon of advancements."})
@Examples("icon of the advancement \"skript:my_advancement\"")
@Since("1.3.2")

public class ExprIcon extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprIcon.class, ItemStack.class, ExpressionType.SIMPLE, "[the] displayed icon of [[the] advancement[s]] %advancements%",
                "[[the] advancement[s]] %advancements%'[s] displayed icon");
    }

    private Expression<Advancement> advancements;

    @Override
    protected @Nullable ItemStack[] get(Event e) {
        for(Advancement advancement : advancements.getArray(e)) {
            return new ItemStack[]{  advancement.getDisplay().icon() };
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "icon of " + advancements.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) exprs[0];
        return true;
    }
}
