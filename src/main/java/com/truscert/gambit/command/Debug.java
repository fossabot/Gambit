package com.truscert.gambit.command;

import com.truscert.gambit.Gambit;
import com.truscert.gambit.game.data.ConfigData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!Gambit.DEBUGGABLE) {
            sender.sendMessage("§eDebug mode is unavailable!");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players may execute this!");
            return true;
        }
        if (args.length == 0) {
            Player player = (Player) sender;
            sender.sendMessage("§eYour location:§f" + player.getLocation());
            com.truscert.gambit.game.data.Debug.getDebugData().playerLocation = player.getLocation();
            player.sendMessage("§eYour location in RAM:§f" + com.truscert.gambit.game.data.Debug.getDebugData().playerLocation);
            Gambit.getInstance().getConfig().
                    set("Debug.playerLocation", com.truscert.gambit.game.data.Debug.getDebugData().playerLocation);
            player.sendMessage(
                    "§eThe location that saved in disk (Server Got): §f" +
                            Gambit.getInstance().getConfig().get("Debug.playerLocation")
            );
            player.sendMessage(
                    "Ct: " + ConfigData.getData().roomCenter + "\n" +
                            "T1: " + ConfigData.getData().roomTeam1 + "\n" +
                            "T2: " + ConfigData.getData().roomTeam2 + "\n"
            );
            return true;
        }
        sender.sendMessage("§cCommand Error!");
        return true;
    }
}