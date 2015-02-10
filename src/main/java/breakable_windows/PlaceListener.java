package breakable_windows;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener {

	//private HashMap<Block, Material> blockList = new HashMap<>();
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void placeBlockEvent(BlockPlaceEvent event) {
		//WIP
	}
	
	public void setAndSaveBlock(final Block theBlock, final Material type) {
		
	}
	
	public void setAndSaveBlock(final Block theBlock, final Material type, final Location theLocation) {
	
	}
}
