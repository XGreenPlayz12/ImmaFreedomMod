package me.StevenLawson.TotalFreedomMod.Listener;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import java.util.Collection;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_ProtectedArea;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

// Implement FreedomOp Remastered methods
public class FreedomListener implements Listener
{
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event)
    {
        if (event.getReason().equals("You logged in from another location") && TFM_AdminList.isSuperAdmin(event.getPlayer()))
        {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event)
    {
        if (event.getFrom() == null || event.getTo() == null)
        {
            return;
        }

        Player player = event.getPlayer();

        if (event.getTo().getBlockX() >= 29999000 || event.getTo().getBlockZ() >= 29999000)
        {
            event.setCancelled(true);
        }
    }

    @SuppressWarnings("null")
    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        String message = event.getMessage();
        Player player = event.getPlayer();
        WorldEditPlugin plugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        if (message.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("set")
                && message.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("sphere")
                && message.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("hsphere")
                && message.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("cyl")
                && message.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("hcyl")
                && message.replaceAll("/", "").split(" ")[0].equalsIgnoreCase("replace"))
        {
            Selection selection = plugin.getSelection(event.getPlayer());
            Location location1 = selection.getMaximumPoint();
            Location location2 = selection.getMinimumPoint();
            String world = selection.getWorld().getName();
            Vector max = location1.toVector();
            Vector min = location2.toVector();
            if (TFM_ProtectedArea.isInProtectedArea(min, max, world))
            {
                if (!TFM_AdminList.isSuperAdmin(player))
                {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You cannot use WorldEdit in a protected area!");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerConsumePotion(PlayerItemConsumeEvent event)
    {
        if (event.getItem().getType() == Material.POTION)
        {
            Collection<PotionEffect> fx = Potion.fromItemStack(event.getItem()).getEffects();
            for (PotionEffect effect : fx)
            {
                if (effect.getType() == PotionEffectType.INVISIBILITY && !TFM_AdminList.isSuperAdmin(event.getPlayer()))
                {
                    event.getPlayer().sendMessage(ChatColor.RED + "Invisibility is not allowed.");
                    event.setCancelled(true);
                }
                if (effect.getAmplifier() < 0)
                {
                    event.getPlayer().sendMessage(ChatColor.RED + "Effects with a negative amplifier are not allowed.");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event)
    {
        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.EGG))
        {
            event.setCancelled(true);
            return;
        }

        Entity spawned = event.getEntity();

        if (spawned instanceof EnderDragon)
        {
            event.setCancelled(true);
        }
        else if (spawned instanceof Ghast)
        {
            event.setCancelled(true);
        }
        else if (spawned instanceof Slime)
        {
            event.setCancelled(true);
        }
        else if (spawned instanceof Giant)
        {
            event.setCancelled(true);
        }
        else if (spawned instanceof Wither)
        {
            event.setCancelled(true);
        }
    }
}
