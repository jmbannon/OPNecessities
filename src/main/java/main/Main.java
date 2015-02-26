/*
==============================================================
File:    StartUp.java
Author:  Jesse Bannon
Bukkit:  1.7.2
Version: 1.0

Server:  Project Zombie
Website: www.projectzombie.net

License: This is open-source code. Please feel
         free to modify or use.
==============================================================
 */

package main;

import org.bukkit.plugin.java.JavaPlugin;

import commands.Item_Commands;
import commands.PersonalWarp_Commands;
import commands.Player_Commands;
import commands.Teleport_Commands;
import commands.AdminWarp_Commands;
import commands.World_Commands;


public class Main extends JavaPlugin {
	
	private Teleport_Commands teleExec;
	private PersonalWarp_Commands pWarpExec;
	private AdminWarp_Commands warpExec;
	private Item_Commands itemExec;
	private Player_Commands playerExec;
	private World_Commands worldExec;
	
	@Override
	public void onEnable() {
		teleExec = new Teleport_Commands(this);
		pWarpExec = new PersonalWarp_Commands(this);
		warpExec = new AdminWarp_Commands(this);
		itemExec = new Item_Commands();
		playerExec = new Player_Commands();
		worldExec = new World_Commands();
		
		pWarpExec.loadConfig();
		warpExec.loadConfig();
		
		this.getCommand("pwarp").setExecutor(pWarpExec);
		
		this.getCommand("warp").setExecutor(warpExec);
		
		this.getCommand("back").setExecutor(teleExec);
		this.getCommand("tp").setExecutor(teleExec);
		this.getCommand("tph").setExecutor(teleExec);
		
		this.getCommand("ci").setExecutor(itemExec);
		this.getCommand("i").setExecutor(itemExec);
		this.getCommand("idb").setExecutor(itemExec);
		
		this.getCommand("gm").setExecutor(playerExec);
		this.getCommand("speed").setExecutor(playerExec);
		
		this.getCommand("time").setExecutor(worldExec);
		this.getCommand("killmobs").setExecutor(worldExec);
		
		this.getLogger().info("OPNecessities Enabled!");
	}
	
	@Override
	public void onDisable() {
		teleExec.onDisable();
		this.getLogger().info("OPNecessities disabled!");
	}
		
}
