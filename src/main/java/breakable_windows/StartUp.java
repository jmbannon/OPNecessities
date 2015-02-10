package breakable_windows;

import org.bukkit.plugin.java.JavaPlugin;


public class StartUp extends JavaPlugin {
	
	private BlockListener window;
	private ScopeZoomListener scope;
	
	@Override
	public void onEnable() {
		this.window = new BlockListener();
		this.scope = new ScopeZoomListener();
		this.getServer().getPluginManager().registerEvents(window, this);
		this.getServer().getPluginManager().registerEvents(scope, this);
		this.getLogger().info("Breakable Windows enabled!");

	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Restoring glass...");
		this.window.disable();
		this.scope.disable();
		this.getLogger().info("Restored glass! Breakable Windows disabled.");
	}	
}
