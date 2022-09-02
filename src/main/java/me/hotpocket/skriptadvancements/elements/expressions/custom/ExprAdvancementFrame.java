package me.hotpocket.skriptadvancements.elements.expressions.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import me.hotpocket.skriptadvancements.utils.AdvancementAPI;
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Custom Advancement Frame")
@Description({"This expression allows you to change the frame of a custom advancement in the advancement creator section."})
@Examples({"set frame of advancement to task",
        "set frame of advancement to goal",
        "set frame of advancement to challenge"})
@RequiredPlugins("Paper")
@Since("1.3")

public class ExprAdvancementFrame extends SimpleExpression<AdvancementAPI.Frame> {

    static {
        if (Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerExpression(ExprAdvancementFrame.class, AdvancementAPI.Frame.class, ExpressionType.SIMPLE,
                    "[the] frame of [the] [last (created|made)] advancement",
                    "[the] [last (created|made)] advancement's frame");
    }

    @Override
    protected @Nullable AdvancementAPI.Frame[] get(Event e) {
        return new AdvancementAPI.Frame[]{AdvancementHandler.frame};
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
        return "frame of last created advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return getParser().isCurrentSection(SecMakeAdvancement.class);
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(AdvancementAPI.Frame.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.frame = (AdvancementAPI.Frame) delta[0];
    }
}
