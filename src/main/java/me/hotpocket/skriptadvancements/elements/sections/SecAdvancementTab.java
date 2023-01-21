package me.hotpocket.skriptadvancements.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.EffectSection;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.utils.CustomUtils;
import me.hotpocket.skriptadvancements.utils.creation.Creator;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Name("Advancement Tab Section")
@Description("Creates a custom advancement tab.")
@Since("1.4")

public class SecAdvancementTab extends EffectSection {

    static {
        Skript.registerSection(SecAdvancementTab.class, "create [a[n]] [new] advancement tab named %string%");
    }

    private Expression<String> name;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult,
                        @Nullable SectionNode sectionNode, @Nullable List<TriggerItem> triggerItems) {
        if (getParser().isCurrentSection(SecAdvancementTab.class)) {
            Skript.error("The advancement tab creation section is not meant to be put inside of another advancement tab creation section.");
            return false;
        }
        if (sectionNode != null) {
            loadOptionalCode(sectionNode);
        }
        name = (Expression<String>) exprs[0];
        return true;
    }

    @Override
    @Nullable
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected TriggerItem walk(Event event) {
        Creator.lastCreatedTab = name.getSingle(event).toLowerCase().replaceAll(" ", "_");
        if (CustomUtils.getAPI().getAdvancementTab(Creator.lastCreatedTab) != null && (CustomUtils.getAPI().getAdvancementTab(Creator.lastCreatedTab).isInitialised() || CustomUtils.getAPI().getAdvancementTab(Creator.lastCreatedTab).isActive())) {
            CustomUtils.getAPI().unregisterAdvancementTab(Creator.lastCreatedTab);
        }
        CustomUtils.getAPI().createAdvancementTab(Creator.lastCreatedTab);
        return walk(event, true);
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "create an advancement tab with the name " + name.toString(event, debug);
    }
}
