package me.hotpocket.skriptadvancements.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.EffectSection;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Name("Root Advancement Creator")
@Description({"Creates a new root advancement."})
@Examples({"create an advancement named \"group/advancement\""})
@Since("1.4")

public class SecCreateAdvancement extends EffectSection {

    static {
        Skript.registerSection(SecCreateAdvancement.class, "(create|make) [a[n]] [new] advancement (named|with [the] name [of]) %string%");
    }

    private Expression<String> advancementName;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult,
                        @Nullable SectionNode sectionNode, @Nullable List<TriggerItem> triggerItems) {
        if(getParser().isCurrentSection(SecCreateAdvancement.class)){
            Skript.error("You cannot use this section inside itself.");
            return false;
        }
        if(sectionNode != null){
            loadOptionalCode(sectionNode);
        }
        advancementName = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    @Nullable
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected TriggerItem walk(Event event) {
        AdvancementHandler.setAdvancementName(advancementName.getSingle(event));
        return walk(event, true);
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "create a new advancement named " + advancementName.toString(event, debug);
    }
}
