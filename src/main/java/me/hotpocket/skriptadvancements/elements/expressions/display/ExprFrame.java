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
import me.hotpocket.skriptadvancements.utils.AdvancementAPI;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Frame")
@Description({"Allows you to get the frame of advancements."})
@Examples("title of the advancement \"adventure/root\"")
@Since("1.3.2")

public class ExprFrame extends SimpleExpression<AdvancementAPI.Frame> {

    static {
        if(Skript.classExists("io.papermc.paper.advancement.AdvancementDisplay"))
            Skript.registerExpression(ExprFrame.class, AdvancementAPI.Frame.class, ExpressionType.SIMPLE, "[the] displayed frame of [[the] advancement[s]] %advancements%",
                "[[the] advancement[s]] %advancements%'[s] displayed frame");
    }

    private Expression<Advancement> advancements;

    @Override
    protected @Nullable AdvancementAPI.Frame[] get(Event e) {
        for(Advancement advancement : advancements.getArray(e)) {
            return new AdvancementAPI.Frame[]{AdvancementAPI.Frame.valueOf(advancement.getDisplay().frame().name())};
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends AdvancementAPI.Frame> getReturnType() {
        return AdvancementAPI.Frame.class;
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
