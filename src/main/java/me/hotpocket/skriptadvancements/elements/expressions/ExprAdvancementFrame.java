package me.hotpocket.skriptadvancements.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecCreateAdvancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprAdvancementFrame extends SimpleExpression<AdvancementFrameType> {

    static {
        Skript.registerExpression(ExprAdvancementFrame.class, AdvancementFrameType.class, ExpressionType.SIMPLE,
                "[the] frame [of [the]] advancement",
                "[the] advancement's frame");
    }

    @Override
    protected @Nullable AdvancementFrameType[] get(Event e) {
        return new AdvancementFrameType[]{ AdvancementHandler.getAdvancementFrame() };
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends AdvancementFrameType> getReturnType() {
        return AdvancementFrameType.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the advancement's frame";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return getParser().isCurrentSection(SecCreateAdvancement.class);
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(AdvancementFrameType.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.setAdvancementFrame((AdvancementFrameType) delta[0]);
    }
}
