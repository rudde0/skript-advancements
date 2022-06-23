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
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

@Name("Delete Advancement")
@Description({"Deletes a custom advancement.",
"Use of this effect is not recommended and it needs to be polished, instead, you should delete advancements on your own by going into your main world folder, going into datapacks, bukkit, and then data. After that, go into the skript-advancements folder and delete files from there."})
@Examples({"delete the advancement \"skream:group/advancement\""})
@Since("1.3")

public class EffDeleteAdvancement extends Effect {

    static {
        Skript.registerEffect(EffDeleteAdvancement.class, "(delete|remove) [[the] advancement[s]] %advancements%");
    }

    private Expression<Advancement> advancement;

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        this.advancement = (Expression<Advancement>) expressions[0];
        return true;
    }

    @Override
    protected void execute(@NonNull Event event) {
        for(Advancement goal : advancement.getAll(event)) {
            if (goal == null) return;
            ArrayList<Advancement> advancements = new ArrayList<>();
            Iterator<Advancement> iterator = Bukkit.getServer().advancementIterator();
            while(iterator.hasNext()) {
                advancements.add(iterator.next());
            }
            if(advancements.contains(goal)) {
                if(goal.getKey().getKey().contains(":")) {
                    if(goal.getKey().getKey().split(":")[0] != "minecraft") {
                        Bukkit.getUnsafe().removeAdvancement(goal.getKey());
                        Bukkit.reloadData();
                    } else {
                        Skript.error("You cannot delete an advancement that is not custom.");
                    }
                } else {
                    Skript.error("You cannot delete an advancement that is not custom.");
                }
            }
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "delete the advancement " + advancement.toString(event, debug);
    }
}
