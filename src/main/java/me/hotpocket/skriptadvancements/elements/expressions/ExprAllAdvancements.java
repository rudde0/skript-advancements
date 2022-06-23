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
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

@Name("All Advancements")
@Description({"Get all of the advancements."})
@Examples("all advancements")
@Since("1.3")

public class ExprAllAdvancements extends SimpleExpression<Advancement> {

    static {
        Skript.registerExpression(ExprAllAdvancements.class, Advancement.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] advancements");
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "Returns a list of advancements";
    }

    @Override
    @Nullable
    protected Advancement[] get(Event event) {
        ArrayList<Advancement> advancements = new ArrayList<>();
        Iterator<Advancement> iterator = Bukkit.getServer().advancementIterator();
        while(iterator.hasNext()) {
            advancements.add(iterator.next());
        }
        return advancements.toArray(new Advancement[advancements.size()]);
    }
}
