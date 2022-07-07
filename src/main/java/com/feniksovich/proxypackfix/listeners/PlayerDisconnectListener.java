package com.feniksovich.proxypackfix.listeners;

import com.feniksovich.proxypackfix.ProxyPackFix;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener implements Listener {

    private final ProxyPackFix plugin;

    public PlayerDisconnectListener(ProxyPackFix plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDisconnect(final PlayerDisconnectEvent e) {
        plugin.getCacheManager().remove(e.getPlayer().getUniqueId());
    }
}
