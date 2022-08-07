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

@Name("Advancement Description")
@Description({"This expression allows you to change the description of a custom advancement in the advancement creator section."})
@Examples("set description of advancement to \"The best advancement.\"")
@RequiredPlugins("Paper")
@Since("1.3")

public class ExprAdvancementDescription extends SimpleExpression<String> {

    static {
        if (Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerExpression(ExprAdvancementDescription.class, String.class, ExpressionType.SIMPLE,
                    "[the] des[cription] of [the] [last (created|made)] advancement",
                    "[the] [last (created|made)] advancement's description");
    }

    @Override
    protected @Nullable String[] get(Event e) {
        return new String[]{AdvancementHandler.description};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "description of last created advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return getParser().isCurrentSection(SecMakeAdvancement.class);
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(String.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.description = (String) delta[0];
    }
}
