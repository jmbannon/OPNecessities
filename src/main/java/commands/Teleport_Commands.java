/*
==============================================================
File:    CommandExec.java
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

import main.Main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport_Commands extends Command_Methods implements CommandExecutor {
	
	public Teleport_Commands(Main plugin) {
		return; /* Do nothing */
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player playerOne = (Player) sender;
		
		/**
		 * Only runs the command if player is OP.
		 */
		//if (!playerOne.isOp())
		//	return true;
		
		/**
		 * /tp <name>
		 *   Teleports player to online player that
		 *   matches character sequence first.
		 */
		//if (cmd.getName().equalsIgnoreCase("tp")) {
		if (this.hasPermission(playerOne, cmd, "tp", "opn.tp")) {
			
			if (args.length == 0) {
				playerOne.sendMessage("/tp <player name>");
				return true;
			}
			
			for (Player tpPlayer : Bukkit.getServer().getOnlinePlayers()) {
				if (tpPlayer.getName().toLowerCase().contains(args[0].toLowerCase())) {
					this.teleport(playerOne, tpPlayer.getLocation());
					return true;
				}
			}
			playerOne.sendMessage("Player not found!");
		}
		/**
		 * /back
		 *  Teleports player back to most recent place of a tele command.
		 */
		//else if(cmd.getName().equalsIgnoreCase("back"))
		else if (this.hasPermission(playerOne, cmd, "back", "opn.back"))
			this.baTeleport(playerOne);
		
		/**
		 * /tph <name>
		 *   Teleports specified player to caller.
		 */
		//else if (cmd.getName().equalsIgnoreCase("tph")) {
		else if (this.hasPermission(playerOne, cmd, "tph", "opn.tph")) {
			
			if (args.length == 0) {
				playerOne.sendMessage("/tp <player name>");
				return true;
			}
			
			for (Player tpPlayer : Bukkit.getServer().getOnlinePlayers()) {
				if (tpPlayer.getName().toLowerCase().contains(args[0].toLowerCase())) {
					this.teleport(tpPlayer, playerOne.getLocation());
					return true;
				}
			}
			playerOne.sendMessage("Player not found!");
		}
		
		
		return true;
	}
	
	/**
	 * Clears HashMap of back warp locations.
	 */
	public void onDisable() {
		this.onDisable();
	}

}
