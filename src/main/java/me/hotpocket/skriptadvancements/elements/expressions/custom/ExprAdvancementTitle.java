package me.hotpocket.skriptadvancements.elements.expressions.custom;

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
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Title")
@Description({"Allowed Changers: SET",
        "This expression allows you to change the title of a custom advancement in the advancement creator section."})
@Examples("set title of advancement to \"My Advancement\"")
@Since("1.3")

public class ExprAdvancementTitle extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAdvancementTitle.class, String.class, ExpressionType.SIMPLE,
                "[the] title of [the] [last (created|made)] advancement",
                "[the] [last (created|made)] advancement's title");
    }

    @Override
    protected @Nullable String[] get(Event e) {
        return new String[]{AdvancementHandler.getTitle()};
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
        if(getParser().isCurrentSection(SecMakeAdvancement.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(String.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.setTitle((String) delta[0]);
    }
}
