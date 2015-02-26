package commands;

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

public class AdminWarp_Commands extends Command_Methods implements CommandExecutor {
	
	private Plugin plugin;
	private File warpConfig = null;
	private FileConfiguration warpFileConfig = null;
	
	public AdminWarp_Commands(Plugin plugin) {
		this.plugin = plugin;
		this.warpConfig = null;
		this.warpFileConfig = null;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player playerOne = (Player) sender;
		
		/**
		 * /warp <args> <project name>
		 *   project warps
		 */
		//if (playerOne.isOp() && cmd.getName().equalsIgnoreCase("warp")) {
		if (this.hasPermission(playerOne, cmd, "warp", "opn.warp")) {
			
			if (args.length == 0) {
				playerOne.sendMessage("/warp <project name>    - warps to project");
				playerOne.sendMessage("/warp c <project name>  - creates project warp");
				playerOne.sendMessage("/warp r <project name>  - removes project warp");
				playerOne.sendMessage("/warp l                 - lists project warps");
			}
			else if (args[0].equalsIgnoreCase("c")) {
				if (!args[1].isEmpty()) {
					this.create(playerOne, args[1], warpFileConfig);
					this.saveConfig();
				} else
					playerOne.sendMessage("/warp c <project name>  - creates project warp");
			}
			else if (args[0].equalsIgnoreCase("r")) {
				if (!args[1].isEmpty()) {
					this.remove(playerOne, args[1], warpFileConfig);
					this.saveConfig();
				} else
					playerOne.sendMessage("/warp r <project name>  - removes project warp");	
			}
			else if (args[0].equalsIgnoreCase("l")) {
				this.list(playerOne, warpFileConfig);
			}
			else
				this.teleWarp(playerOne, args[0], warpFileConfig);
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
	private void teleWarp(final Player sender, final String prName, 
			final FileConfiguration file) {
		
		final String path = "warps." + prName;
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
	private void create(final Player sender, final String prName,
			final FileConfiguration file) {
		
		final String path = "warps." + prName;
		
		file.set("warps", prName);
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
	private void remove(final Player sender, final String prName,
			final FileConfiguration file) {
		
		final String path = "warps." + prName;
		file.set(path, null);
		
		sender.sendMessage(prName + " deleted.");
	}
	
	/**
	 * Lists all of a player's project warps.
	 * 
	 * @param sender Sender of the warp list command.
	 * @param file YAML file that stores project location information.
	 */
	private void list(final Player sender, final FileConfiguration file) {
		sender.sendMessage("projects: ");
		for (String key : file.getConfigurationSection("warps").getKeys(false)) {
			sender.sendMessage(" - " + key);
		}
	}
	
	/**
	 * Loads file from plugin folder.
	 */
	public void loadConfig() {
		if (warpConfig == null) {
			warpConfig = new File(plugin.getDataFolder(), "warps.yml");
		}
		
		warpFileConfig = new YamlConfiguration();
		warpFileConfig = YamlConfiguration.loadConfiguration(warpConfig);
		this.saveConfig();
	}
	
	/**
	 * Saves all changes to file and file configuration.
	 */
	private void saveConfig() {
		if (warpConfig == null || warpFileConfig == null)
			return;
							
		try {
			warpFileConfig.save(warpConfig);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + warpFileConfig, e);
		}
	}
	
}