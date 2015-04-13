package com.advancedmods.advancedfoods;

import com.advancedmods.advancedfoods.common.CommonProxy;
import com.advancedmods.advancedfoods.common.generic.AFCreativeTabFood;
import com.advancedmods.advancedfoods.common.generic.AFCreativeTabIngredients;
import com.advancedmods.advancedfoods.common.generic.AFCreativeTabMisc;
import com.advancedmods.advancedfoods.common.generic.AFCreativeTabSauces;
import com.advancedmods.advancedfoods.core.AFProps;
import com.advancedmods.advancedfoods.core.AFRegistry;
import com.advancedmods.advancedfoods.core.handler.ConfigurationHandler;
import com.advancedmods.amcore.core.mod.BaseMod;
import com.advancedmods.amcore.core.mod.updater.UpdateManager;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dennisbonke on 10-2-2015.
 */
@Mod(name = AFProps.name, modid = AFProps.modid, version = AFProps.version, dependencies = AFProps.dependencies, modLanguage = "java", canBeDeactivated = false, acceptedMinecraftVersions = AFProps.MC_VERSION)
public class AdvancedFoods extends BaseMod {

	@SidedProxy(clientSide = AFProps.clientproxy, serverSide = AFProps.commonproxy)
	public static CommonProxy proxy;
	@Instance(AFProps.modid)
	public static AdvancedFoods instance;
	public static Logger log = LogManager.getLogger("AdvancedFoods");
	public static final String updateURL = "https://raw.github.com/AdvancedMods/AdvancedFoods/master/VERSION";
    public static final String releaseURL = "http://ci.zsinfo.nl/job/AdvancedFoods/lastSuccessfulBuild";
    public static ConfigurationHandler config;
    // Creative Tabs
    public static CreativeTabs food = new AFCreativeTabFood("Food");
    public static CreativeTabs misc = new AFCreativeTabMisc("Misc");
    public static CreativeTabs ingredients = new AFCreativeTabIngredients("Ingredients");
    public static CreativeTabs sauces = new AFCreativeTabSauces("Sauces");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		// Starting mod
		log.info("Starting Advanced Foods version " + AFProps.version + "...");
		log.info("Entering Pre-Init ...");
		// Do Pre-Init stuff
        // Configs
        log.info("Setting up configs...");
        try {
            ConfigurationHandler.init(event.getSuggestedConfigurationFile());
            log.info("Configs setup");
        } catch (Exception e) {
            log.error("Could not load or create config, using default values");
        }
        // Update manager
        if (config.checkUpdates) {
            try {
                log.info("Starting Update manager for Advanced Foods...");
                UpdateManager.registerUpdater(new UpdateManager(this, updateURL, releaseURL));
                log.info("Update Manager for Advanced Foods started");
            } catch (Exception e) {
                log.error("Error starting update checker, printing stacktrace...");
                e.printStackTrace();
            }
        } else if (!config.checkUpdates) {
            log.warn("Update checker disabled");
        } else {
            FMLLog.bigWarning("Error reading config, enabling Update checker and using default values");
            UpdateManager.registerUpdater(new UpdateManager(this, updateURL, releaseURL));
        }
        // Registry Items and Blocks
        log.info("Registering Items and Blocks...");
        AFRegistry.registerItemsAndBlocks();
        log.info("Items and Blocks registered");
		log.info("Pre-Init complete");

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		log.info("Entering Init ...");
		// Init handlers
		log.info("Registering Handlers...");
        AFRegistry.initHandlers();
		log.info("Handlers registered");
        // Grass seed hooks
		log.info("Adding Grass Seed Hooks...");
        AFRegistry.addGrassSeedsHooks();
		log.info("Grass Seed Hooks added");
        // Recipes
        log.info("Registering Recipes...");
        AFRegistry.registerRecipes();
        log.info("Recipes Registered");
        log.info("Init complete");
        log.info("Mod loaded");

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		log.info("Entering Post-Init ...");
		log.info("Post-Init complete");

	}

	@Override
	public String getModId() {
		return AFProps.modid;
	}

	@Override
	public String getModName() {
		return AFProps.name;
	}

	@Override
	public String getModVersion() {
		return AFProps.version;
	}

}
