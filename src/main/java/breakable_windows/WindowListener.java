package breakable_windows;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

import com.shampaggon.crackshot.events.WeaponHitBlockEvent;

public class WindowListener implements Listener {
	
	private HashMap<Block, Material> blockList = new HashMap<>();

	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void meleeBlockBreakEvent(BlockBreakEvent event) {
		if (isGlass(event.getBlock())) {
			chunckBreak(event.getBlock());
			if (event.getPlayer().getItemInHand() == null ||
					event.getPlayer().getItemInHand().getType() == Material.AIR) { 
				event.getPlayer().damage(0.5);
			}
			
		}
	}
		
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void projectileBlockBreakEvent(WeaponHitBlockEvent event) {
		if (isGlass(event.getBlock())) 
			chunckBreak(event.getBlock());
		else if (isNonEffectBlock(event.getBlock()))
			event.getProjectile().remove();		
	}
	
	public boolean isNonEffectBlock(final Block nonEffectBlock) {
		return (nonEffectBlock.getType() == Material.SNOW
				|| nonEffectBlock.getType() == Material.CARPET
				|| nonEffectBlock.getType() == Material.STONE_PLATE
				|| nonEffectBlock.getType() == Material.WOOD_PLATE
				|| nonEffectBlock.getType() == Material.FENCE
				|| nonEffectBlock.getType() == Material.NETHER_FENCE);
	}
	
	public void storeAndBreakGlass(final Block glassBlock) {
		//final Material type = glassBlock.getType();
		
		blockList.put(glassBlock, glassBlock.getType());
		
		//if (type != Material.GLASS && type != Material.THIN_GLASS
		//		&& type != Material.STAINED_GLASS && type != Material.STAINED_GLASS_PANE)
			glassBlock.setType(Material.AIR);
		//else
		//	glassBlock.breakNaturally();
				
	}
	
	public boolean isGlass(final Block glassBlock) {
		return (glassBlock.getType() == Material.GLASS
				|| glassBlock.getType() == Material.THIN_GLASS
				|| glassBlock.getType() == Material.STAINED_GLASS
				|| glassBlock.getType() == Material.STAINED_GLASS_PANE
				|| glassBlock.getType() == Material.GLOWSTONE
				|| glassBlock.getType() == Material.REDSTONE_LAMP_ON
				|| glassBlock.getType() == Material.REDSTONE_LAMP_OFF
				|| glassBlock.getType() == Material.BEACON
				|| glassBlock.getType() == Material.TORCH);
	}
	
	public void checkToStoreGlass(final Block glassBlock) {
		if (this.isGlass(glassBlock)) 
			this.storeAndBreakGlass(glassBlock);
	}
	
	public void checkConstBLocks(final Block glassBlock) {
		this.checkToStoreGlass(glassBlock.getRelative(1, 0, 0));
        this.checkToStoreGlass(glassBlock.getRelative(-1, 0, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, 1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, -1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, 0, 1));
        this.checkToStoreGlass(glassBlock.getRelative(0, 0, -1));
	}
	
	public void checkFormation1(final Block glassBlock) {
		this.checkToStoreGlass(glassBlock.getRelative(1, 1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(-1, -1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, 1, 1));
        this.checkToStoreGlass(glassBlock.getRelative(0, -1, -1));
	}
	
	public void checkFormation2(final Block glassBlock) {
		this.checkToStoreGlass(glassBlock.getRelative(1, -1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(-1, 1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, -1, 1));
        this.checkToStoreGlass(glassBlock.getRelative(0, 1, -1));
	}
	
	public void checkFormation3(final Block glassBlock) {
		this.checkToStoreGlass(glassBlock.getRelative(1, 1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(-1, 1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, 1, 1));
        this.checkToStoreGlass(glassBlock.getRelative(0, 1, -1));
	}
	
	public void checkFormation4(final Block glassBlock) {
		this.checkToStoreGlass(glassBlock.getRelative(1, -1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(-1, -1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, -1, 1));
        this.checkToStoreGlass(glassBlock.getRelative(0, -1, -1));
	}
	
	public void checkFormation5(final Block glassBlock) {
		this.checkToStoreGlass(glassBlock.getRelative(-1, 1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(-1, -1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, 1, -1));
        this.checkToStoreGlass(glassBlock.getRelative(0, -1, -1));
	}
	
	public void checkFormation6(final Block glassBlock) {
		this.checkToStoreGlass(glassBlock.getRelative(1, 1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(1, -1, 0));
        this.checkToStoreGlass(glassBlock.getRelative(0, 1, 1));
        this.checkToStoreGlass(glassBlock.getRelative(0, -1, 1));
	}
	
	public void chunckBreak(final Block glassBlock) {
		final Random rand = new Random();
		final int randNum = rand.nextInt((6-1) + 1) + 1;
		
		glassBlock.getWorld().playSound(glassBlock.getLocation(), Sound.GLASS, 1, 1);
		this.storeAndBreakGlass(glassBlock);
		
		this.checkConstBLocks(glassBlock);
			switch(randNum) {
			case 1: this.checkFormation1(glassBlock); break;
			case 2: this.checkFormation2(glassBlock); break;
			case 3: this.checkFormation4(glassBlock); break;
			case 4: this.checkFormation4(glassBlock); break;
			case 5: this.checkFormation5(glassBlock); break;
			case 6: this.checkFormation6(glassBlock); break;
			default: break;
			}
	}
	
	public void recursiveBreak(final Block glassBlock) {
		
		this.storeAndBreakGlass(glassBlock);
		
		if (glassBlock.getRelative(1, 0, 0).getType() == Material.GLASS
				|| glassBlock.getRelative(1, 0, 0).getType() == Material.THIN_GLASS)
			recursiveBreak(glassBlock.getRelative(1, 0, 0));
		
		if (glassBlock.getRelative(-1, 0, 0).getType() == Material.GLASS
				|| glassBlock.getRelative(-1, 0, 0).getType() == Material.THIN_GLASS)
			recursiveBreak(glassBlock.getRelative(-1, 0, 0));
		
		if (glassBlock.getRelative(0, 1, 0).getType() == Material.GLASS
				|| glassBlock.getRelative(0, 1, 0).getType() == Material.THIN_GLASS)
			recursiveBreak(glassBlock.getRelative(0, 1, 0));
		
		if (glassBlock.getRelative(0, -1, 0).getType() == Material.GLASS
				|| glassBlock.getRelative(0, -1, 0).getType() == Material.THIN_GLASS)
			recursiveBreak(glassBlock.getRelative(0, -1, 0));
		
		if (glassBlock.getRelative(0, 0, 1).getType() == Material.GLASS
				|| glassBlock.getRelative(0, 0, 1).getType() == Material.THIN_GLASS)
			recursiveBreak(glassBlock.getRelative(0, 0, 1));
		
		if (glassBlock.getRelative(0, 0, -1).getType() == Material.GLASS
				|| glassBlock.getRelative(0, 0, -1).getType() == Material.THIN_GLASS)
			recursiveBreak(glassBlock.getRelative(0, 0, -1));		
	}
	
	public void restoreGlass() {
		
		for (Entry<Block, Material> entry : blockList.entrySet()) {
			entry.getKey().setType(entry.getValue());
		}
		blockList.clear();
	}

	
}
