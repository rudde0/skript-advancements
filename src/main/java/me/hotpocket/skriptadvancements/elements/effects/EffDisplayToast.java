package me.hotpocket.skriptadvancements.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import me.hotpocket.skriptadvancements.Skriptadvancements;
import me.hotpocket.skriptadvancements.advancementcreator.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

public class EffDisplayToast extends Effect {

    static {
        Skript.registerEffect(EffDisplayToast.class, "(send|display) [the] [custom] toast to %players% (named|titled) %string% (with|using) [[the] icon] %itemtype% (with|using) [[the] frame] %frame%");
    }

    private Expression<Player> players;
    private Expression<String> title;
    private Expression<ItemType> icon;
    private Expression<Advancement.Frame> frame;

    @Override
    @SuppressWarnings({"unchecked"})
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        players = (Expression<Player>) expressions[0];
        title = (Expression<String>) expressions[1];
        icon = (Expression<ItemType>) expressions[2];
        frame = (Expression<Advancement.Frame>) expressions[3];
        return true;
    }

    @Override
    protected void execute(@NonNull Event event) {
        for(Player player : players.getArray(event)) {
            UltimateAdvancementAPI.getInstance(Skriptadvancements.getInstance()).displayCustomToast(player, new ItemStack(icon.getSingle(event).getMaterial()), title.getSingle(event), AdvancementFrameType.valueOf(frame.getSingle(event).toString().toUpperCase()));
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "send toast to " + players.toString(event, debug) + " named " + title.toString(event, debug) + " with the icon " + icon.toString(event, debug) + " and the frame " + frame.toString(event, debug);
    }
}
