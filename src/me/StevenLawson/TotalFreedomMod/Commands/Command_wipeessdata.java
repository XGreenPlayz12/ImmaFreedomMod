package me.StevenLawson.TotalFreedomMod.Commands;

import java.io.File;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.ONLY_CONSOLE, blockHostConsole = true)
@CommandParameters(description = "Removes essentials playerdata", usage = "/<command>", aliases = "wipeuserdata")
public class Command_wipeessdata extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (!server.getPluginManager().isPluginEnabled("Essentials"))
        {
            playerMsg("Essentials is not enabled on this server");
            return true;
        }

        TFM_Util.adminAction(sender.getName(), "Wiping Essentials playerdata", true);

        TFM_Util.deleteFolder(new File(server.getPluginManager().getPlugin("Essentials").getDataFolder(), "userdata"));

        playerMsg("All playerdata deleted.");
        return true;
    }
}
