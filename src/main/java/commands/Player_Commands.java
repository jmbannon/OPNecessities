package commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Player_Commands extends Command_Methods implements CommandExecutor {
	
	public Player_Commands() {
		return; /* Do nothing. */
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player playerOne = (Player) sender;
		
		//if (!playerOne.isOp())
		//	return true;
		
		//if (cmd.getName().equalsIgnoreCase("gm")) {
		if (this.hasPermission(playerOne, cmd, "gm", "opn.gm")) {
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
		
		//else if (cmd.getName().equalsIgnoreCase("speed")) {
		else if (this.hasPermission(playerOne, cmd, "speed", "opn.speed")) {
			if (args.length == 1 && args[0].matches("[0-9]+") && args[0].length() <= 2)
				playerOne.setFlySpeed(Float.valueOf(args[0])/100);
			else
				playerOne.sendMessage("/speed <0-99>");
		}
		
		return true;
	}
}
