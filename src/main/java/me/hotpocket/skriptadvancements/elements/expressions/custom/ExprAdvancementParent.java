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
import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Parent")
@Description({"Allowed Changers: SET",
        "This expression allows you to change the parent of a custom advancement in the advancement creator section."})
@Examples("set parent of advancement to \"skript-advancements:group/advancement\"")
@Since("1.3")

public class ExprAdvancementParent extends SimpleExpression<Advancement> {

    static {
        Skript.registerExpression(ExprAdvancementParent.class, Advancement.class, ExpressionType.SIMPLE,
                "[the] parent of [the] [last (created|made)] advancement",
                "[the] [last (created|made)] advancement's parent");
    }

    @Override
    protected @Nullable Advancement[] get(Event e) {
        return new Advancement[]{Bukkit.getAdvancement(AdvancementHandler.getParent())};
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Advancement> getReturnType() {
        return Advancement.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "parent of last created advancement";
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
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(Advancement.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.setParent((Advancement) delta[0]);
    }
}
