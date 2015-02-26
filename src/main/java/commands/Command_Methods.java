/*
==============================================================
File:    Teleport.java
Author:  Jesse Bannon
Bukkit:  1.7.2
Version: 1.0

Server:  Project Zombie
Website: www.projectzombie.net

License: This is open-source code. Please feel
         free to modify or use.
==============================================================
 */

package commands;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Command_Methods {

	private static HashMap<Player, Location> locations = new HashMap<>();
	
	/**
	 * Stores current player location to teleport back to and
	 * teleports the player to the destination.
	 * 
	 * @param sender Sender of the teleport command.
	 * @param destination Location to be teleported to.
	 */
	public void teleport(final Player sender, final Location destination) {
		locations.put(sender, sender.getLocation());
		sender.teleport(destination);
	}
	
	/**
	 * Teleports the player back to their most recent location of
	 * a teleport command.
	 * 
	 * @param sender Sender of the back command.
	 */
	public void baTeleport(final Player sender) {
		final Location backLocation = locations.get(sender);
		
		if (backLocation == null)
			sender.sendMessage("You haven't teleported anywhere yet!");
		else 
			this.teleport(sender, backLocation);
		
	}
	
	/**
	 * Clears hashmap of back locations.
	 */
	public void onDisable() {
		locations.clear();
	}
	
	public boolean hasPermission(final Player player, final Command cmd,
			final String command, final String permission) {
		return ((player.isOp() || player.hasPermission(permission)) 
				&& cmd.getName().equalsIgnoreCase(command));
	}
	
}
