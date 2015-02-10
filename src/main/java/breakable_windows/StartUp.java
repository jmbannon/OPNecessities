package breakable_windows;

import org.bukkit.plugin.java.JavaPlugin;


public class StartUp extends JavaPlugin {
	
	private BlockBreakListener window;
	private ScopeZoomListener scope;
	
	@Override
	public void onEnable() {
		this.window = new BlockBreakListener();
		this.scope = new ScopeZoomListener();
		
		this.getServer().getPluginManager().registerEvents(window, this);
		this.getServer().getPluginManager().registerEvents(scope, this);
		
		this.getLogger().info("Breakable Windows enabled!");

	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Restoring blocks...");
		this.window.disable();
		this.scope.disable();
		//this.place.disable();
		this.getLogger().info("Restored blocks! Breakable Windows disabled.");
	}	
}
