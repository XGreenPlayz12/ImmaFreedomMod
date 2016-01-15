package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.World.TFM_BuilderWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Allows anyone to go to the builder world.", usage = "/<command>", aliases = "builderworld")
public class Command_builder extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (TFM_ConfigEntry.BUILDER_WORLD_ENABLED.getBoolean())
        {
            TFM_BuilderWorld.getInstance().sendToWorld(sender_p);
        }
        else
        {
            playerMsg("The Builder World is currently disabled.");
        }
        return true;
    }
}
