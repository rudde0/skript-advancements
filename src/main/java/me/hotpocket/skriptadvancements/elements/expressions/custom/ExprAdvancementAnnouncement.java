package me.hotpocket.skriptadvancements.elements.expressions.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class ExprAdvancementAnnouncement extends SimpleExpression<Boolean> {

    static {
        if (Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerExpression(ExprAdvancementAnnouncement.class, Boolean.class, ExpressionType.SIMPLE,
                    "[the] announcement of [the] [last (created|made)] advancement",
                    "[the] [last (created|made)] advancement's announcement");
    }

    @Override
    protected @Nullable Boolean[] get(Event e) {
        return new Boolean[]{AdvancementHandler.announce};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "announcement of last created advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return getParser().isCurrentSection(SecMakeAdvancement.class);
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(Boolean.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.announce = ((Boolean) delta[0]);
    }
}
