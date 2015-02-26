package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class World_Commands extends Command_Methods implements CommandExecutor {

	public World_Commands() {
		return; /* Do nothing. */
	}
		
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player playerOne = (Player) sender;
		
		if (this.hasPermission(playerOne, cmd, "time", "opn.time")) {
			if (args.length == 1 && args[0].matches("[0-9]+") && args[0].length() < 9) {
				final int temp = (Integer.valueOf(args[0]) - 6) * 1000 % 24000;
				playerOne.getWorld().setTime(temp);
			}
			else
				playerOne.sendMessage("/time <0-23>");
		}
		
		else if (this.hasPermission(playerOne, cmd, "killmobs", "opn.killmobs")) {
			for (int i = 0; i < playerOne.getWorld().getEntities().size(); i++) {
				if (isEnemyMob(playerOne.getWorld().getEntities().get(i))) {
					playerOne.getWorld().getEntities().remove(i);
				}
			}
		}
		
		return true;
	}
	
	private boolean isEnemyMob(final Entity entity) {
		final EntityType type = entity.getType();
		return (type == EntityType.ZOMBIE 		|| type == EntityType.PIG_ZOMBIE
				|| type == EntityType.CAVE_SPIDER 	|| type == EntityType.ENDERMAN
				|| type == EntityType.SPIDER 		|| type == EntityType.SKELETON
				|| type == EntityType.CREEPER 		|| type == EntityType.ENDER_DRAGON);
	}
	
}
