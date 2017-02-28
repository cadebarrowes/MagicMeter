package com.tadninja.magicmeter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

/* every 2 ticks run this */

public class OnTick extends BukkitRunnable implements Listener {

	private ItemStack meter = new ItemStack(Material.GOLD_HOE, 1);
	private ItemMeta meterMeta = meter.getItemMeta();
	
	MainClass mainPlugin;
	
	public OnTick(MainClass plugin) {
		
		meterMeta.setDisplayName("Magic Meter");
		
		List<String> lore = new ArrayList<String>();
		lore.add("Üse \"/mm stop\" to free offhand.");
		lore.add("Do not remove or replace, as it will");
		lore.add("delete any item you try to place here!");
		meterMeta.setLore(lore);  
		
		meter.setItemMeta(meterMeta);
		
		this.mainPlugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	

	@Override
	/* Add one to everyone's magic amount to slowly refill magic meter */
	/* Give a golden hoe with durability to make their magic meter appear the same as their "Magic Amount" score */
	public void run() {

		// add 1 to everyone's Meter that isn't full (32) or disabled (-1)

		if (Bukkit.getOnlinePlayers().size() != 0) {
			for (Player everyplayer : Bukkit.getOnlinePlayers()) {
				// for every player

				if (everyplayer.hasMetadata("Magic Amount")) {
					// for every player that has metadata

					if (everyplayer.getMetadata("Magic Amount").get(0).asInt() > -1) {
						// for every player that has metadata greater than -1
						if (everyplayer.getMetadata("Magic Amount").get(0).asInt() < 128) {
							// for every player that has metadata less than 128

							everyplayer.setMetadata("Magic Amount", new FixedMetadataValue(mainPlugin,
									everyplayer.getMetadata("Magic Amount").get(0).asInt() + 1));
							// add one to the current metadata
						}
						
						meter.setDurability((short) (32 - (everyplayer.getMetadata("Magic Amount").get(0).asInt())/4 ));
						everyplayer.getInventory().setItemInOffHand(meter);
						// give a golden hoe to everyone with an enabled meter
					}

				}

			}
		}

	}

}