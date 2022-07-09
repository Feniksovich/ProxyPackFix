package com.feniksovich.proxypackfix.platform.bungee;

import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeDisconnectListener implements Listener {

    private final BungeeProxyPackFix plugin;

    public BungeeDisconnectListener(BungeeProxyPackFix plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDisconnect(final PlayerDisconnectEvent e) {
        plugin.getCacheManager().remove(e.getPlayer().getUniqueId());
    }
}
