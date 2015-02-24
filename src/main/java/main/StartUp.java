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


public class StartUp extends JavaPlugin {
	
	private TeleportExec teleExec;
	private PWarp pWarpExec;
	private Warp warpExec;
	private ItemExec itemExec;
	private PlayerExec playerExec;
	
	@Override
	public void onEnable() {
		teleExec = new TeleportExec(this);
		pWarpExec = new PWarp(this);
		warpExec = new Warp(this);
		itemExec = new ItemExec(this);
		playerExec = new PlayerExec(this);
		
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
		
		this.getLogger().info("OPNecessities Enabled!");
	}
	
	@Override
	public void onDisable() {
		teleExec.onDisable();
		this.getLogger().info("OPNecessities disabled!");
	}
		
}
