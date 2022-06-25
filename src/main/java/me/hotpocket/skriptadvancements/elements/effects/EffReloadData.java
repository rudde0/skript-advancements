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
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Reload Data")
@Description({"This is NOT the same as reloading the entire server, just Minecraft's data. This data includes things like advancements and loot tables.",
"An example of this effect being used is when you make a mistake and you would like to reload advancements now, rather than later."})
@Examples({"reload bukkit data"})
@Since("1.3.1")

public class EffReloadData extends Effect {

    static {
        Skript.registerEffect(EffReloadData.class, "reload [bukkit['s]] data");
    }

    @Override
    protected void execute(Event e) {
        Bukkit.reloadData();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "reload data";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        return true;
    }
}
