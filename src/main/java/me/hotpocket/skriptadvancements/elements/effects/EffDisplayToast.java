package me.hotpocket.skriptadvancements.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import de.tr7zw.nbtapi.NBTItem;
import me.hotpocket.skriptadvancements.SkriptAdvancements;
import me.hotpocket.skriptadvancements.advancementcreator.Advancement;
import me.hotpocket.skriptadvancements.advancementcreator.shared.ItemObject;
import me.hotpocket.skriptadvancements.advancementcreator.trigger.ImpossibleTrigger;
import me.hotpocket.skriptadvancements.utils.AdvancementUtils;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@Name("Display Toast")
@Description({"This effect displays a custom toast to a player."})
@Examples({"display toast to player named \"You're So Cool!\" with icon gold ingot with frame challenge"})
@Since("1.3.1")

public class EffDisplayToast extends Effect {

    static {
        Skript.registerEffect(EffDisplayToast.class,
                "(send|display|show) [the] [custom] toast to %players% (named|titled) %string% (with|using) [[the] icon] %itemtype% (with|using) [[the] frame] %frame%");
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
        ItemStack item = new ItemStack(icon.getSingle(event).getMaterial());
        item.setItemMeta(icon.getSingle(event).getItemMeta());
        NBTItem nbtItem = new NBTItem(item);
        Advancement advancement = new Advancement(
                new NamespacedKey("custom-toast-message", "toast/new_toast_message"),
                new ItemObject().setItem(icon.getSingle(event).getMaterial()).setNbt(nbtItem.getCompound().toString()),
                new TextComponent(title.getSingle(event)),
                new TextComponent(""))
                .setFrame(frame.getSingle(event))
                .setAnnounce(false)
                .addTrigger("custom", new ImpossibleTrigger())
                .makeChild(NamespacedKey.minecraft("adventure/root"));
        if(Bukkit.getAdvancement(advancement.getId()) == null) {
            advancement.activate(true);
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    for(Player player : players.getArray(event)) {
                        AdvancementUtils.revokeAdvancement(player, Bukkit.getAdvancement(advancement.getId()));
                        Bukkit.getUnsafe().removeAdvancement(advancement.getId());
                        Bukkit.reloadData();
                    }
                }
            };
            for(Player player : players.getArray(event)) {
                AdvancementUtils.grantAdvancement(player, Bukkit.getAdvancement(advancement.getId()));
            }
            task.runTaskLater(SkriptAdvancements.getInstance(), 1);
        }
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "send toast to " + players.toString(event, debug) + " named " + title.toString(event, debug) + " with the icon " + icon.toString(event, debug) + " and the frame " + frame.toString(event, debug);
    }
}
