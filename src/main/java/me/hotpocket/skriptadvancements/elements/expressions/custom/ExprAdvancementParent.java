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
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Parent")
@Description({"This expression allows you to change the parent of a custom advancement in the advancement creator section."})
@Examples("set parent of advancement to \"skript-advancements:group/advancement\"")
@RequiredPlugins("Paper")
@Since("1.3")

public class ExprAdvancementParent extends SimpleExpression<Advancement> {

    static {
        if(Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerExpression(ExprAdvancementParent.class, Advancement.class, ExpressionType.SIMPLE,
                "[the] parent of [the] [last (created|made)] advancement",
                "[the] [last (created|made)] advancement's parent");
    }

    @Override
    protected @Nullable Advancement[] get(Event e) {
        return new Advancement[]{Bukkit.getAdvancement(AdvancementHandler.parent)};
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
        return getParser().isCurrentSection(SecMakeAdvancement.class);
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(Advancement.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.parent = ((Advancement) delta[0]).getKey();
    }
}
