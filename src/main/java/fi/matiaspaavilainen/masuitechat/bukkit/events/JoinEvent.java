package fi.matiaspaavilainen.masuitechat.bukkit.events;

import fi.matiaspaavilainen.masuitechat.bukkit.MaSuiteChat;
import fi.matiaspaavilainen.masuitecore.core.objects.PluginChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private MaSuiteChat plugin;

    public JoinEvent(MaSuiteChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            Player p = e.getPlayer();
            if (p != null) {
                new PluginChannel(plugin, p, new Object[]{"MaSuiteChat", "SetGroup", p.getUniqueId().toString(), plugin.getPrefix(p), plugin.getSuffix(p)}).send();
            }
        }, 10);
    }
}