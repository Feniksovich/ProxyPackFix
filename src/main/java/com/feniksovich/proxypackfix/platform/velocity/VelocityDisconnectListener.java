package com.feniksovich.proxypackfix.platform.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;

public class VelocityDisconnectListener {

    private final VelocityProxyPackFix plugin;

    public VelocityDisconnectListener(VelocityProxyPackFix plugin) {
        this.plugin = plugin;
    }

    @Subscribe
    public void onPlayerChat(DisconnectEvent e) {
        plugin.getCacheManager().remove(e.getPlayer().getUniqueId());
    }
}
