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
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Title")
@Description({"This expression allows you to change the title of a custom advancement in the advancement creator section."})
@Examples("set title of advancement to \"My Advancement\"")
@RequiredPlugins("Paper")
@Since("1.3")

public class ExprAdvancementTitle extends SimpleExpression<String> {

    static {
        if(Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerExpression(ExprAdvancementTitle.class, String.class, ExpressionType.SIMPLE,
                "[the] title of [the] [last (created|made)] advancement",
                "[the] [last (created|made)] advancement's title");
    }

    @Override
    protected @Nullable String[] get(Event e) {
        return new String[]{AdvancementHandler.title};
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
        return "title of last created advancement";
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
        AdvancementHandler.title = (String) delta[0];
    }
}
