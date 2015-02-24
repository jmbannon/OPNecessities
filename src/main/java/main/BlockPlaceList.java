package breakable_windows;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceList implements Listener {

	private HashMap<Block, Material> blockList = new HashMap<>();

	
	@EventHandler(priority = EventPriority.NORMAL)
	public void placeBlockEvent(BlockPlaceEvent event) {
		if (placable(event.getBlockPlaced()) && event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
			blockList.put(event.getBlockAgainst(), event.getBlockAgainst().getType());
			event.setBuild(true);
		}
	}
	
	public boolean placable(final Block theBlock) {
		return (theBlock.getType() == Material.SPONGE
				|| theBlock.getType() == Material.TORCH);
	}
	
	public void disable() {
		for (Entry<Block, Material> entry : blockList.entrySet()) {
			entry.getKey().setType(entry.getValue());
		}
		blockList.clear();
	}
}
