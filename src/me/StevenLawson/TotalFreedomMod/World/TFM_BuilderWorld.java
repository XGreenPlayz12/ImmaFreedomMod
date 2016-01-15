package me.StevenLawson.TotalFreedomMod.World;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.TFM_GameRuleHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class TFM_BuilderWorld extends TFM_CustomWorld
{
    private static final String GENERATION_PARAMETERS = TFM_ConfigEntry.FLATLANDS_GENERATE_PARAMS.getString();
    private static final String WORLD_NAME = "builderworld";

    private TFM_BuilderWorld()
    {
    }

    @Override
    protected World generateWorld()
    {
        if (!TFM_ConfigEntry.BUILDER_WORLD_ENABLED.getBoolean())
        {
            return null;
        }

        WorldCreator worldCreator = new WorldCreator(WORLD_NAME);
        worldCreator.generateStructures(false);
        worldCreator.type(WorldType.NORMAL);
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.generator(new CleanroomChunkGenerator(GENERATION_PARAMETERS));

        World world = Bukkit.getServer().createWorld(worldCreator);

        world.setSpawnFlags(false, false);
        world.setSpawnLocation(0, 50, 0);

        Block welcomeSignBlock = world.getBlockAt(0, 50, 0);
        welcomeSignBlock.setType(Material.SIGN_POST);
        org.bukkit.block.Sign welcomeSign = (org.bukkit.block.Sign) welcomeSignBlock.getState();

        org.bukkit.material.Sign signData = (org.bukkit.material.Sign) welcomeSign.getData();
        signData.setFacingDirection(BlockFace.NORTH);

        welcomeSign.setLine(0, ChatColor.RED + "ImmaFreedom");
        welcomeSign.setLine(1, ChatColor.DARK_GRAY + "---");
        welcomeSign.setLine(2, ChatColor.DARK_GREEN + "BuilderWorld");
        welcomeSign.update();

        TFM_GameRuleHandler.commitGameRules();

        return world;
    }

    public static TFM_BuilderWorld getInstance()
    {
        return TFM_BuilderWorldHolder.INSTANCE;
    }

    private static class TFM_BuilderWorldHolder
    {
        private static final TFM_BuilderWorld INSTANCE = new TFM_BuilderWorld();
    }
}
