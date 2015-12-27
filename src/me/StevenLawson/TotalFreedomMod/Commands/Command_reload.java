package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.BOTH)
@CommandParameters(description = "Reloads the server safely.", usage = "/<command>", aliases = "rl")
public class Command_reload extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.bcastMsg("Server is being reloaded by " + sender.getName + ", expect lag.", ChatColor.GREEN);

        server.reload();
        
        TFM_Util.bcastMsg("Reload completed! Thanks for your patience.", ChatColor.GREEN);
        
        return true;
    }
}
