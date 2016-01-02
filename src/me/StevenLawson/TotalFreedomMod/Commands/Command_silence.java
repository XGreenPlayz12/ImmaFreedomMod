package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Listener.TFM_PlayerListener;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "", usage = "")
public class Command_silence extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (TFM_PlayerListener.SILENCE_CHAT.contains(sender.getName()))
        {
            TFM_PlayerListener.SILENCE_CHAT.remove(sender.getName());
            sender.sendMessage("Now listening to chat");
        }
        else
        {
            TFM_PlayerListener.SILENCE_CHAT.add(sender.getName());
            sender.sendMessage("Chat will be silenced");
        }
        return true;
    }
}
