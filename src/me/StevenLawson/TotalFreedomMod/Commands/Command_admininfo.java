package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Apply for admin.", usage = "/<command>", aliases = "ai")
public class Command_admininfo extends TFM_Command
{

    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            sender.sendMessage(ChatColor.GOLD + "============HOW TO APPLY============");
            sender.sendMessage(ChatColor.AQUA + "If you want to be an administrator, you need to follow these steps:");
            sender.sendMessage(ChatColor.AQUA + "First of all, you need to need to make an account on our forum: http://immafreedom.eu/");
            sender.sendMessage(ChatColor.AQUA + "When you create your account, just go to this link: " + ChatColor.BLUE + "http://immafreedom.boards.net/board/3/super-admin-applications?tour=howtoapply" + ChatColor.AQUA + " and follow the steps carefully!"
            sender.sendMessage(ChatColor.RED + "If you register now, you must wait 5 days before you post your application!");
            sender.sendMessage(ChatColor.GOLD + "============HOW TO APPLY============");
            return true;
        }
        else
        {
            return false;
        }
    }
}
