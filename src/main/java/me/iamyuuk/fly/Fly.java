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
        if (Bukkit.getPluginCommand("fly") != null) {
            Objects.requireNonNull(Bukkit.getPluginCommand("fly")).setExecutor(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("插件已关闭");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("fly.use")) {
                sender.sendMessage("你没有权限");
                return true;
            }
            player.setAllowFlight(!player.getAllowFlight());
            sender.sendMessage("飞行" + (player.getAllowFlight() ? "启动" : "禁用") + "成功");
        } else {
            // 可能是插件执行或者是控制台
            sender.sendMessage("你不是玩家");
        }
        return true;
    }
}
