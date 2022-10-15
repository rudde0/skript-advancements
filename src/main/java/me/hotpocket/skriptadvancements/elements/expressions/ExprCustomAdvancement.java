package me.hotpocket.skriptadvancements.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.util.AdvancementKey;
import me.hotpocket.skriptadvancements.SkriptAdvancements;
import me.hotpocket.skriptadvancements.utils.CustomUtils;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprCustomAdvancement extends SimpleExpression<Advancement> {

    static {
        Skript.registerExpression(ExprCustomAdvancement.class, Advancement.class, ExpressionType.SIMPLE,
                "[the] custom advancement %string%");
    }

    private Expression<String> advancementName;

    @Override
    protected @Nullable Advancement[] get(Event e) {
        if (advancementName.getSingle(e).contains("/")) {
            String[] name = advancementName.getSingle(e).split("/");
            if (UltimateAdvancementAPI.getInstance(SkriptAdvancements.getInstance()).getAdvancementTab(name[0]) != null) {
                if (CustomUtils.getAPI().getAdvancement(new AdvancementKey(name[0], name[1])) != null)
                    return new Advancement[]{CustomUtils.getAPI().getAdvancement(new AdvancementKey(name[0], name[1]))};
            }
        }
        return null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Advancement> getReturnType() {
        return Advancement.class;
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "the custom advancement " + advancementName.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancementName = (Expression<String>) exprs[0];
        return true;
    }
}
