package otherwisejunk.pvptoggle.spigot.plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public class PvPToggle extends JavaPlugin
{
    public static Map<Player, Boolean> playerPvPStates;

    @Override
    public void onEnable() {
        playerPvPStates = new HashMap();
        getLogger().info("[PvPToggle] Mod successfully enabled.");

    }
    @Override
    public void onDisable() {
        getLogger().info("[PvPToggle] Mod Disabled.");
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,
     final String label, final String[] args){
        //If the triggering message is from the Console we don't care.
        if (! (sender instanceof Player)) {            
            return true;
        }

        final Player player = (Player) sender;
        
        if(command.getName().equalsIgnoreCase("pvp")){
            Boolean pvpEnabled;
            if(playerPvPStates.containsKey(player)){
                pvpEnabled = !playerPvPStates.get(player);

                playerPvPStates.put(player, pvpEnabled);
            }
            else{
                pvpEnabled = true;
                playerPvPStates.put(player, pvpEnabled);
            }

            if(pvpEnabled){
                player.setPlayerListName(ChatColor.RED + player.getName());
                player.setDisplayName(ChatColor.RED + player.getName());
            }
            else{
                player.setPlayerListName(ChatColor.WHITE + player.getName());
                player.setDisplayName(ChatColor.WHITE + player.getName());
            }
            sender.sendMessage("Set your PvP Flag to: " + pvpEnabled);
        }
        return true;
    }
}