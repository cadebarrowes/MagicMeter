package com.tadninja.magicmeter;

import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

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
	// https://bukkit.org/threads/right-click-with-item-event.73590/
	@SuppressWarnings("deprecation")
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){
	    Player p = event.getPlayer();
	 
	    if(event.getAction().equals(Action.RIGHT_CLICK_AIR)){
	        if(p.getItemInHand().getType() == Material.BLAZE_POWDER){
	            Fireball fire = p.getWorld().spawn(event.getPlayer().getLocation().add(new Vector(0.0D, 1.0D, 0.0D)), Fireball.class);
	            fire.setFireTicks(0);
	            fire.setShooter(p);
	        }
	        else if(p.getItemInHand().getType() == Material.BLAZE_ROD){
	            //Do whatever
	        }
	    }
	}
}