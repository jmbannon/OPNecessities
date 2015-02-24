package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class PWarp extends Teleport implements CommandExecutor {
	
	private Plugin plugin;
	private File pWarpConfig;
	private FileConfiguration pWarpFileConfig;
	
	public PWarp(StartUp plugin) {
		this.plugin = plugin;
		this.pWarpConfig = null;
		this.pWarpFileConfig = null;
	}
	
	/**
	 * /pwarp <args> <project name>
	 *   project warps
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		final Player playerOne = (Player) sender;
		
		if (playerOne.isOp() && cmd.getName().equalsIgnoreCase("pwarp")) {
			
			if (args.length == 0) {
				playerOne.sendMessage("/pwarp <project name>    - warps to project");
				playerOne.sendMessage("/pwarp c <project name>  - creates project warp");
				playerOne.sendMessage("/pwarp r <project name>  - removes project warp");
				playerOne.sendMessage("/pwarp l                 - lists project warps");
			}
 			else if (args[0].equalsIgnoreCase("c")) {
				if (!args[1].isEmpty()) {
					this.create(playerOne, args[1], pWarpFileConfig);
					this.saveConfig();
				} else
					playerOne.sendMessage("/pwarp c <project name>  - creates project warp");
			}
			else if (args[0].equalsIgnoreCase("r")) {
				if (!args[1].isEmpty()) {
					this.remove(playerOne, args[1], pWarpFileConfig);
					this.saveConfig();
				} else
					playerOne.sendMessage("/pwarp r <project name>  - removes project warp");	
			}
			else if (args[0].equalsIgnoreCase("l")) {
				this.list(playerOne, pWarpFileConfig);
			}
			else
				this.teleWarp(playerOne, args[0], pWarpFileConfig);
		}
		return true;
	}
	
	
	/**
	 * Teleports player to the specified project name.
	 * 
	 * @param sender Sender of the project teleport command.
	 * @param prName Project name to teleport to.
	 * @param file YAML file that stores project location information.
	 */
	public void teleWarp(final Player sender, final String prName, 
			FileConfiguration file) {
		
		final String path = sender.getUniqueId().toString().replace("-", "") + "." + prName;
		final String worldPath = path + ".world";
		final String coordPath = path + ".coords";
		
		if (file.contains(path) && file.contains(worldPath) && file.contains(coordPath)) {
			final Vector vector = file.getVector(coordPath);
			final Location prLoc = new Location(Bukkit.getWorld(file.getString(worldPath)), 
					vector.getX(), vector.getY(), vector.getZ());
			this.teleport(sender, prLoc);
				
		} else
			sender.sendMessage("Project location does not exist!");
	}
	
	/**
	 * Creates a project warp with the specified name.
	 * 
	 * @param sender Sender of the warp create command.
	 * @param prName Name of the project warp to create.
	 * @param file YAML file that stores project location information.
	 */
	public void create(final Player sender, final String prName,
			FileConfiguration file) {
		
		if (file == null)
			sender.sendMessage("Shit is null!");
			
		final String path = sender.getUniqueId().toString().replace("-", "") + "." + prName;
		
		file.set(sender.getUniqueId().toString(), prName);
		file.set(path + ".world", sender.getWorld().getName());
		file.set(path + ".coords", sender.getLocation().toVector());
		
		sender.sendMessage(prName + " created.");
	}
	
	
	/**
	 * Deletes a project warp of the specified name.
	 * 
	 * @param sender Sender of the warp delete command.
	 * @param prName Name of the project warp to delete.
	 * @param file YAML file that stores project location information.
	 */
	public void remove(final Player sender, final String prName,
			FileConfiguration file) {
		
		final String path = sender.getUniqueId().toString().replace("-", "") + "." + prName;
		file.set(path, null);
		
		sender.sendMessage(prName + " deleted.");
	}
	
	/**
	 * Lists all of a player's project warps.
	 * 
	 * @param sender Sender of the warp list command.
	 * @param file YAML file that stores project location information.
	 */
	public void list(final Player sender, final FileConfiguration file) {
		sender.sendMessage("projects: ");
		for (String key : file.getConfigurationSection(sender.getUniqueId().toString().replace("-", "")).getKeys(false)) {
			sender.sendMessage(" - " + key);
		}
	}
	
	/**
	 * Loads file from plugin folder.
	 */
	public void loadConfig() {
		if (pWarpConfig == null) {
			pWarpConfig = new File(plugin.getDataFolder(), "pwarps.yml");
		}
		
		pWarpFileConfig = new YamlConfiguration();
		pWarpFileConfig = YamlConfiguration.loadConfiguration(pWarpConfig);
		this.saveConfig();
	}
	
	/**
	 * Saves all changes to file and file configuration.
	 */
	public void saveConfig() {
		if (pWarpConfig == null || pWarpFileConfig == null)
			return;
							
		try {
			pWarpFileConfig.save(pWarpConfig);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + pWarpFileConfig, e);
		}
	}

}
