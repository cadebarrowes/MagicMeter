package com.tadninja.magicmeter;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import com.tadninja.magicmeter.commands.MagicMeter;

public class MainClass extends JavaPlugin {
	
	public MainClass plugin;


	@SuppressWarnings("deprecation")
	public void onEnable() {

		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		this.getServer().getScheduler().runTaskTimer(this, new OnTick(this), 0L, 1L);
		
		getCommand("mm").setExecutor(new MagicMeter(this));

		logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");

	}

	public void onDisable() {

		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");

	}
	
}