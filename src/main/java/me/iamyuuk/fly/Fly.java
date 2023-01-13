package me.iamyuuk.fly;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Fly extends JavaPlugin implements CommandExecutor {
    static Task task;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info(
                """
                          ______ _      \s
                         |  ____| |     \s
                         | |__  | |_   _\s
                         |  __| | | | | |
                         | |    | | |_| |
                         |_|    |_|\\__, |
                                    __/ |
                                   |___/\s
                                
                        开发者：ObcbO、宇宇YuuK
                        """
        );
        task = new Task();
        task.runTaskTimerAsynchronously(this, 200, 20);
        if (Bukkit.getPluginCommand("fly") != null) {
            Objects.requireNonNull(Bukkit.getPluginCommand("fly")).setExecutor(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        task.cancel();
        getLogger().info("插件已关闭");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("fly.use")) {
                sender.sendMessage(" §6米§f客 §8>> 你没有权限");
                return true;
            }
            boolean allowFlight = player.getAllowFlight();
            player.setAllowFlight(!allowFlight);
            sender.sendMessage(" §6米§f客 §8>> " + (player.getAllowFlight() ? "§a飞行启动" : "§c飞行禁用") + "成功");
        } else {
            // 可能是插件执行或者是控制台
            sender.sendMessage(" §6米§f客 §8>> §c你不是玩家");
        }
        return true;
    }
}
