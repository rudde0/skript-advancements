package me.hotpocket.skriptadvancements.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import me.hotpocket.skriptadvancements.utils.CustomUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class EffCustomToast extends Effect {

    static {
        Skript.registerEffect(EffCustomToast.class, "(show|display) [the] toast using [[the] icon] %itemtype%(,| and) [[the] title] %string%(,| and) [[the] frame] %frame% to %players%");
    }

    private Expression<ItemType> icon;
    private Expression<String> title;
    private Expression<AdvancementFrameType> frame;
    private Expression<Player> players;

    @Override
    protected void execute(Event e) {
        ItemStack item = new ItemStack(icon.getSingle(e).getMaterial());
        item.setItemMeta(icon.getSingle(e).getItemMeta());
        for (Player player : players.getAll(e))
            CustomUtils.getAPI().displayCustomToast(player, item, title.getSingle(e), frame.getSingle(e));
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "display the toast using the icon " + icon.toString(e, debug) + ", the title " + title.toString(e, debug) + " and the frame " + frame.toString(e, debug) + " to " + players.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        icon = (Expression<ItemType>) exprs[0];
        title = (Expression<String>) exprs[1];
        frame = (Expression<AdvancementFrameType>) exprs[2];
        players = (Expression<Player>) exprs[3];
        return true;
    }
}
