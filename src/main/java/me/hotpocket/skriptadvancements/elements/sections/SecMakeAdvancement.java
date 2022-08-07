package me.hotpocket.skriptadvancements.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.*;
import ch.njol.skript.lang.EffectSection;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.utils.AdvancementHandler;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Name("Advancement Creator Section")
@Description({"Creates a new advancement."})
@Examples({"create a new advancement named \"group/advancement\""})
@RequiredPlugins("Paper")
@Since("1.3")

public class SecMakeAdvancement extends EffectSection {

    static {
        if(Skript.methodExists(Material.class, "getTranslationKey"))
            Skript.registerSection(SecMakeAdvancement.class, "create [a[n]] [new] advancement named %string%");
    }

    private Expression<String> name;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult,
                        @Nullable SectionNode sectionNode, @Nullable List<TriggerItem> triggerItems) {
        if(getParser().isCurrentSection(SecMakeAdvancement.class)){
            Skript.error("The advancement creation section is not meant to be put inside of another advancement creation section.");
            return false;
        }
        if(sectionNode != null){
            loadOptionalCode(sectionNode);
        }
        name = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    @Nullable
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected TriggerItem walk(Event event) {
        AdvancementHandler.name = name.getSingle(event).toLowerCase().replaceAll(" ", "_");
        return walk(event, true);
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "create an advancement with the name " + name.toString(event, debug);
    }
}
