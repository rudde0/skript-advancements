package me.hotpocket.skriptadvancements.elements.expressions.custom;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.hotpocket.skriptadvancements.SkriptAdvancements;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Advancement Icon")
@Description({"This expression allows you to change the icon of a custom advancement in the advancement creator section.",
        "Some blocks are not accepted. An example of these blocks would be a carrot plant."})
@Examples("set icon of advancement to diamond")
@RequiredPlugins("Paper")
@Since("1.3")

public class ExprAdvancementIcon extends SimpleExpression<ItemType> {

    static {
        if (Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerExpression(ExprAdvancementIcon.class, ItemType.class, ExpressionType.SIMPLE,
                    "[the] icon of [the] [last (created|made)] advancement",
                    "[the] [last (created|made)] advancement's icon");
    }

    @Override
    protected @Nullable ItemType[] get(Event e) {
        return new ItemType[]{new ItemType(AdvancementHandler.icon)};
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
        return "icon of last created advancement";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return getParser().isCurrentSection(SecMakeAdvancement.class);
    }

    @Override
    public @Nullable Class<?>[] acceptChange(Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET ? CollectionUtils.array(ItemType.class) : null);
    }

    @Override
    public void change(Event e, @Nullable Object[] delta, Changer.ChangeMode mode) {
        AdvancementHandler.icon = SkriptAdvancements.typeToStack((ItemType) delta[0]);
    }
}
