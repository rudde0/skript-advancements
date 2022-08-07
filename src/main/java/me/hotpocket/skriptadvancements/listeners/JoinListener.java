package me.hotpocket.skriptadvancements.listeners;

import me.hotpocket.skriptadvancements.SkriptAdvancements;
import me.hotpocket.skriptadvancements.utils.UpdateChecker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                new UpdateChecker(SkriptAdvancements.getInstance(), 96702).getVersion(version -> {
                    if(!SkriptAdvancements.getInstance().getDescription().getVersion().equals(version)) {
                        event.getPlayer().sendMessage("§eA new version of §lSkript-Advancements §eis available!\n§bDownload Here:§r https://www.spigotmc.org/resources/skript-advancements-skript-addon.96702/");
                    }
                });
            }
        }.runTaskLater(SkriptAdvancements.getInstance(), 20);
    }
}
