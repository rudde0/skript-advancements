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
import org.bukkit.advancement.Advancement;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Delete Advancement")
@Description({"Allows you to delete a custom advancement. (Deleting will also result in the deletion of it's children.)"})
@Examples({"# This will delete all of the custom advancements.", "delete all advancements", "", "# This will delete one advancement.", "delete the advancement \"skript-advancements:group/advancement\""})
@Since("1.3.2")

public class EffDeleteAdvancement extends Effect {

    static {
        Skript.registerEffect(EffDeleteAdvancement.class, "delete [[the] advancement[s]] %advancements%");
    }

    private Expression<Advancement> advancements;

    @Override
    protected void execute(Event e) {
        for(Advancement advancement : advancements.getArray(e)) {
            if(Skript.classExists("io.papermc.paper.advancement.AdvancementDisplay")) {
                for (Advancement child : advancement.getChildren()) {
                    Bukkit.getUnsafe().removeAdvancement(child.getKey());
                }
            }
            Bukkit.getUnsafe().removeAdvancement(advancement.getKey());
        }
        Bukkit.reloadData();
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "delete the advancement " + advancements.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        advancements = (Expression<Advancement>) exprs[0];
        return true;
    }
}
