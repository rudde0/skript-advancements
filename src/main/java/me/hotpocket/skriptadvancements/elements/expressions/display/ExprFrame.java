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
import org.jetbrains.annotations.Nullable;

@Name("Advancement Frame")
@Description({"Allows you to get the frame of advancements."})
@Examples("title of the advancement \"adventure/root\"")
@Since("1.3.2")

public class ExprFrame extends SimpleExpression<me.hotpocket.skriptadvancements.advancementcreator.Advancement.Frame> {

    static {
        Skript.registerExpression(ExprFrame.class, me.hotpocket.skriptadvancements.advancementcreator.Advancement.Frame.class, ExpressionType.SIMPLE, "[the] frame of [[the] advancement[s]] %advancements%",
                "[[the] advancement[s]] %advancements%'[s] frame");
    }

    private Expression<Advancement> advancements;

    @Override
    protected @Nullable me.hotpocket.skriptadvancements.advancementcreator.Advancement.Frame[] get(Event e) {
        for(Advancement advancement : advancements.getArray(e)) {
            return new me.hotpocket.skriptadvancements.advancementcreator.Advancement.Frame[]{me.hotpocket.skriptadvancements.advancementcreator.Advancement.Frame.valueOf(advancement.getDisplay().frame().name())};
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends me.hotpocket.skriptadvancements.advancementcreator.Advancement.Frame> getReturnType() {
        return me.hotpocket.skriptadvancements.advancementcreator.Advancement.Frame.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "frame of " + advancements.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) exprs[0];
        return true;
    }
}
