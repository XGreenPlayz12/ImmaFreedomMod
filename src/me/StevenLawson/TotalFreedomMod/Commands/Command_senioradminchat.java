package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SENIOR, source = SourceType.BOTH)
@CommandParameters(
        description = "SeniorChat - Talk privately with other senior admins. Using <command> itself will toggle SeniorAdminChat on and off for all messages.",
        usage = "/<command> [message...]",
        aliases = "p")
public class Command_senioradminchat extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            if (senderIsConsole)
            {
                playerMsg("Only in-game players can toggle AdminChat.");
                return true;
            }

            TFM_PlayerData userinfo = TFM_PlayerData.getPlayerData(sender_p);
            userinfo.setSeniorChat(!userinfo.inSeniorChat());
            playerMsg("Toggled Senior Chat " + (userinfo.inSeniorChat() ? "on" : "off") + ".");
        }
        else
        {
            TFM_Util.seniorChatMessage(sender, StringUtils.join(args, " "), senderIsConsole);
        }

        return true;
    }
}
