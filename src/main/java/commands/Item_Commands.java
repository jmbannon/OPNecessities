package commands;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Item_Commands extends Command_Methods implements CommandExecutor {
	
	public Item_Commands() {
		return; /* Do nothing */
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		final Player playerOne = (Player) sender;
		
		//if (!playerOne.isOp())
		//	return true;
		
		//if (cmd.getName().equalsIgnoreCase("ci")) {
		if (this.hasPermission(playerOne, cmd, "ci", "opn.ci")) {
			playerOne.getInventory().clear();
		}
		
		
		//else if (cmd.getName().equals("i")) {
		else if (this.hasPermission(playerOne, cmd, "i", "opn.i")) {
			if (args.length == 0)
				playerOne.sendMessage("/i <itemID> <amount>");
			
			else if (args.length == 1 && !args[0].isEmpty()) {
				final String[] parts = args[0].split(":");
				int amount = 1;
				byte data = 0;
				
				if (args.length == 2) {
					if (args[1].matches("[0-9]+"))
						amount = Integer.valueOf(args[1]);
				}
				
				if (Material.matchMaterial(parts[0]) != null) {
					if (parts.length == 2) {
						if (parts[1].matches("[0-9]+"))
							data = Byte.valueOf(parts[1]);
					}
					playerOne.getInventory().addItem(new ItemStack(Material.matchMaterial(parts[0]), amount, (short)0, data));
				}
				else 
					playerOne.sendMessage(parts[0] + " is not a valid item.");
				
			}
			else
				playerOne.sendMessage("/i <itemID> <amount>");
		}
		
		//else if (cmd.getName().equals("idb")) {
		else if (this.hasPermission(playerOne, cmd, "idb", "opn.idb")) {
			final ItemStack temp = playerOne.getItemInHand();
			playerOne.sendMessage(temp.getData().toString());
			playerOne.sendMessage(temp.getTypeId() + ":" + String.valueOf(temp.getData().getData()));
		}
		return true;
		
	}
}
