package breakable_windows;

import org.bukkit.plugin.java.JavaPlugin;


public class StartUp extends JavaPlugin {
	
	private WindowListener window;
	
	@Override
	public void onEnable() {
		this.window = new WindowListener();
		this.getServer().getPluginManager().registerEvents(window, this);
		this.getLogger().info("Breakable Windows enabled!");

	}
	
	@Override
	public void onDisable() {
		this.getLogger().info("Restoring glass...");
		this.window.restoreGlass();
		this.getLogger().info("Restored glass! Breakable Windows disabled.");
	}	
}
