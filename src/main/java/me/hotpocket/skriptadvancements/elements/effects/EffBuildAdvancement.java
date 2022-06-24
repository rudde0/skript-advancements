package me.hotpocket.skriptadvancements.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.hotpocket.skriptadvancements.Skriptadvancements;
import me.hotpocket.skriptadvancements.elements.AdvancementHandler;
import me.hotpocket.skriptadvancements.elements.sections.SecMakeAdvancement;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

@Name("Build Advancement")
@Description({"Builds the last created advancement made with the advancement creation section."})
@Examples({"build the last created advancement"})
@Since("1.3")

public class EffBuildAdvancement extends Effect {

    static {
        Skript.registerEffect(EffBuildAdvancement.class, "(build|finish|make|complete|activate) [the] [last (created|made)] advancement");
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        if(getParser().isCurrentSection(SecMakeAdvancement.class)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void execute(@NonNull Event event) {
        if(Bukkit.getAdvancement(AdvancementHandler.lastCreatedAdvancement.getId()) == null) {
            AdvancementHandler.buildAdvancement();
            if(Skriptadvancements.getInstance().getConfig().getConfigurationSection("reload").getBoolean("on-create")) {
                Bukkit.reloadData();
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "build the last created advancement";
    }
}
