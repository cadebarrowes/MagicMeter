package com.tadninja.magicmeter.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import com.tadninja.magicmeter.MainClass;
//import com.tadninja.magicmeter;

public class MagicMeter implements CommandExecutor, Listener {

	public static Player player;
	
	MainClass mainPlugin;
	
	public MagicMeter(MainClass plugin) {
		
		this.mainPlugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	/*
	 * 
	 * 
	 * 
	 * */
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// sender is the one initiating the command, label is what they typed

		/* Makes sure only player can run command */
		if (!(sender instanceof Player)) {

			sender.sendMessage("You must be a player to use this command!");
			return false;

		}

		/* sets offhand to golden hoe with durability to match meter */
		Player player = (Player) sender;

		/* FOR THE METADATA -1 = off, 0 is empty and 64 is full */
		
		//they typed /mm and something else
		if (args.length == 1) { 
			
			// if they typed /mm start 
			if (args[0].equals("start")) {
				return start();
			}
			// if they typed /mm stahp 
			if (args[0].equals("stop")) {
				return stop();
			}
		}
		return false;
	}
	private boolean start() {
		
		// if they don't have a value for Magic Amount
		if (!(player.hasMetadata("Magic Amount"))) { 
			
			player.sendMessage(ChatColor.GREEN + "Starting MagicMeter");
			
			// set their value to zero so it will be added to every tick
			player.setMetadata("Magic Amount", new FixedMetadataValue(mainPlugin, 0));
			
			return true; //stahp here we done
			
		} 
		// if it comes this far we know they have a value for Magic Amount
		
		if (player.getMetadata("Magic Amount").get(0).asInt()==(-1)) {
			// or if it is -1 set their value to zero so it will be added to every tick
			player.setMetadata("Magic Amount", new FixedMetadataValue(mainPlugin, 0));
			
			return true;
		}
		// if it comes this far we know Magic Meter is still going
		
		player.sendMessage(ChatColor.GRAY + "Magic Meter is still going!");
		
		return false;
		
	}
	private boolean stop() {
		
		// if they don't have a value for Magic Amount
		if (!(player.hasMetadata("Magic Amount"))) { 
			
			player.sendMessage(ChatColor.GRAY + "You haven't started Magic Meter yet!");
			
			return false; //stahp here we done
			
		} 
		// if it comes this far we know they have a value for Magic Amount
		
		if (player.getMetadata("Magic Amount").get(0).asInt()==(-1)) {
			
			player.sendMessage(ChatColor.GRAY + "You haven't started Magic Meter yet!");
			
			return false; //stahp here we done
			
		}
		// if it comes to this point we know Magic Meter is going
		
		player.sendMessage(ChatColor.RED + "Stopping MagicMeter");
		
		player.setMetadata("Magic Amount", new FixedMetadataValue(mainPlugin, -1));
		
		player.getInventory().setItemInOffHand(new ItemStack(Material.AIR)); //remove golden hoe
		
		return true;
	}

}
