package me.iamyuuk.fly;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Task extends BukkitRunnable {
    static Set<Player> flying = new CopyOnWriteArraySet<>();
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            GameMode gameMode = player.getGameMode();
            if (player.getAllowFlight() && player.isFlying() &&
                gameMode != GameMode.CREATIVE && gameMode != GameMode.SPECTATOR) {
                int now = Math.max(player.getFoodLevel() - 1, 0);
                player.setFoodLevel(now);
                if (now == 0) {
                    flying.remove(player);
                    player.setAllowFlight(false);
                }
            }
        }
    }
}
