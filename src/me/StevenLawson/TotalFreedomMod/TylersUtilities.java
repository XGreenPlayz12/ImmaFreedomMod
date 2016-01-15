package me.StevenLawson.TotalFreedomMod;

// This is to fix things that haven't been fixed yet in TFM
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TylersUtilities
{

    public static boolean muffledBlowup(Player player)
    {
        return player.getWorld().createExplosion(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 4f, false, false);
    }

    public static void killPlayer(Player player)
    {
        player.setHealth(0.0);
    }

    public static int fakeKillPlayer(Player player)
    {
        return Bukkit.broadcastMessage(player.getName() + " was killed by an admin");
    }

    public static boolean isDoubleJumper(Player player)
    {
        return TFM_PlayerData.getPlayerData(player).isDoubleJumper();
    }

    public static void setDoubleJumper(Player player, boolean state)
    {
        TFM_PlayerData.getPlayerData(player).setDoubleJumper(state);
    }

    public static boolean inGod(Player player)
    {
        return TFM_PlayerData.getPlayerData(player).inGod();
    }

    public static void setGod(Player player, boolean enabled)
    {
        TFM_PlayerData.getPlayerData(player).setGod(enabled);
    }
}
