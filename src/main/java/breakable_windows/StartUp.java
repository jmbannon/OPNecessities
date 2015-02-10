package breakable_windows;

import org.bukkit.plugin.java.JavaPlugin;


public class StartUp extends JavaPlugin {
	
	private BlockBreakListener window;
	private ScopeZoomListener scope;
	private BlockPlaceList place;
	
	@Override
	public void onEnable() {
		this.window = new BlockBreakListener();
		this.scope = new ScopeZoomListener();
		this.place = new BlockPlaceList();
		
		this.getServer().getPluginManager().registerEvents(place, this);
		this.getServer().getPluginManager().registerEvents(window, this);
		this.getServer().getPluginManager().registerEvents(scope, this);
		
		this.getLogger().info("Breakable Windows enabled!");

	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Restoring glass...");
		this.window.disable();
		this.scope.disable();
		//this.place.disable();
		this.getLogger().info("Restored glass! Breakable Windows disabled.");
	}	
}
