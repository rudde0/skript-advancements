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
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecCreateAdvancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprAdvancementIcon extends SimpleExpression<ItemType> {

    static {
        Skript.registerExpression(ExprAdvancementIcon.class, ItemType.class, ExpressionType.SIMPLE,
                "[the] icon [of [the]] advancement",
                "[the] advancement's icon");
    }

    @Override
    protected @Nullable ItemType[] get(Event e) {
        return new ItemType[]{ new ItemType(AdvancementHandler.getAdvancementIcon()) };
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends ItemType> getReturnType() {
        return ItemType.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the advancement's icon";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return getParser().isCurrentSection(SecCreateAdvancement.class);
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(ItemType.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.setAdvancementIcon(((ItemType) delta[0]).getMaterial());
    }
}
