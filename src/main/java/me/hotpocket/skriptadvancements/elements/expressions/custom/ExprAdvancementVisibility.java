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
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Custom Advancement Visibility")
@Description({"This expression allows you to change the visibility of a custom advancement in the advancement creator section."})
@Examples("set visibility of advancement to true")
@RequiredPlugins("Paper")
@Since("1.3")

public class ExprAdvancementVisibility extends SimpleExpression<Boolean> {

    static {
        if (Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerExpression(ExprAdvancementVisibility.class, Boolean.class, ExpressionType.SIMPLE,
                    "[the] visibility of [the] [last (created|made)] advancement",
                    "[the] [last (created|made)] advancement's visibility");
    }

    @Override
    protected @Nullable Boolean[] get(Event e) {
        return new Boolean[]{AdvancementHandler.hidden};
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
        return "visibility of last created advancement";
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
        AdvancementHandler.hidden = ((Boolean) delta[0]);
    }
}
