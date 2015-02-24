package main;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerExec implements CommandExecutor {

	Plugin plugin;
	
	public PlayerExec(StartUp plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player playerOne = (Player) sender;
		
		if (!playerOne.isOp())
			return true;
		
		if (cmd.getName().equalsIgnoreCase("gm")) {
			if (args.length == 1 && args[0].matches("[0-3]+")) {
				switch(args[0]) {
				case "0": playerOne.setGameMode(GameMode.SURVIVAL); break;
				case "1": playerOne.setGameMode(GameMode.CREATIVE); break;
				case "2": playerOne.setGameMode(GameMode.ADVENTURE); break;
				default: playerOne.sendMessage("Invalid gamemode");
				}
			} else {
				playerOne.sendMessage("/gm <gamemode>");
				playerOne.sendMessage("0 - Survival");
				playerOne.sendMessage("1 - Creative");
				playerOne.sendMessage("2 - Adventure");
			}
		}
		
		else if (cmd.getName().equalsIgnoreCase("speed")) {
			if (args.length == 1 && args[0].matches("[0-9]+") && args[0].length() == 1)
				playerOne.setFlySpeed(Float.valueOf(args[0])/10);
			else
				playerOne.sendMessage("/speed <0-9>");
		}
		
		return true;
	}
}
