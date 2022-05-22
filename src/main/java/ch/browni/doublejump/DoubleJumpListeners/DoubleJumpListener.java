package ch.browni.doublejump.DoubleJumpListeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;

public class DoubleJumpListener implements Listener {
    private HashMap<Player, Boolean>  cooldown = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.setAllowFlight(true);
        cooldown.put(event.getPlayer(), true);
    }

    @EventHandler
    public void onFlyt(PlayerToggleFlightEvent event){
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE){
            event.setCancelled(true);
            if (cooldown.get(player)){
                return;
            }
            event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().setY(1));
            cooldown.put(player, false);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (event.getPlayer().isOnGround()){
            cooldown.put(player,  false);
        }
    }
    @EventHandler
    public void gamemodeChange(PlayerGameModeChangeEvent event){
        Player player = event.getPlayer();
        if (event.getNewGameMode() == GameMode.ADVENTURE || event.getNewGameMode() == GameMode.SURVIVAL){
            player.setAllowFlight(true);
        }
    }
}
