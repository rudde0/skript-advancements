package me.hotpocket.skriptadvancements.elements.expressions;

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
import me.hotpocket.skriptadvancements.advancementcreator.Advancement;
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Frame")
@Description({"Allowed Changers: SET",
        "This expression allows you to change the frame of a custom advancement in the advancement creator section."})
@Examples({"set frame of advancement to task",
        "set frame of advancement to goal",
        "set frame of advancement to challenge"})
@Since("1.3")

public class ExprAdvancementFrame extends SimpleExpression<Advancement.Frame> {

    static {
        Skript.registerExpression(ExprAdvancementFrame.class, Advancement.Frame.class, ExpressionType.SIMPLE,
                "[the] frame of [the] [last (created|made)] advancement",
                "[the] [last (created|made)] advancement's frame");
    }

    @Override
    protected @Nullable Advancement.Frame[] get(Event e) {
        return new Advancement.Frame[]{AdvancementHandler.getFrame()};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Advancement.Frame> getReturnType() {
        return Advancement.Frame.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "frame of last created advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if(getParser().isCurrentSection(SecMakeAdvancement.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(Advancement.Frame.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.setFrame((Advancement.Frame) delta[0]);
    }
}
