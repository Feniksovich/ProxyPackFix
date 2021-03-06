package com.feniksovich.proxypackfix.listeners;

import com.feniksovich.proxypackfix.ProxyPackFix;
import com.feniksovich.proxypackfix.packets.ResourcePackClientboundPacket;
import com.feniksovich.proxypackfix.packets.ResourcePackServerboundPacket;
import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.Protocolize;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

public class ResourcePackClientboundPacketListener extends AbstractPacketListener<ResourcePackClientboundPacket> {

    private final ProxyPackFix plugin;

    public ResourcePackClientboundPacketListener(ProxyPackFix plugin) {
        super(ResourcePackClientboundPacket.class, Direction.DOWNSTREAM, 1);
        this.plugin = plugin;
    }

    @Override
    public void packetReceive(PacketReceiveEvent<ResourcePackClientboundPacket> e) {
        plugin.debugLog("PacketReceived: " + e.packet().toString());

        if (e.packet().getHash().isEmpty() || e.packet().getHash() == null) {
            plugin.getLogger().warning("§e= = = = = = WARNING = = = = = =");
            plugin.getLogger().warning("§eSent resource pack has no hash. Caching will be disabled for this resource pack.");
            plugin.getLogger().warning("§eProxyPackFix features won't work for this resource pack due to inability to uniquely identify it.");
            plugin.getLogger().warning("§eYou must specify a hash for this resource pack to fix this.");
            plugin.getLogger().warning("§eResource Pack URL: " + e.packet().getUrl());
            plugin.getLogger().warning("§e= = = = = = WARNING = = = = = =");

            return;
        }

        if (plugin.getCacheManager().isPresent(e.player().uniqueId(), e.packet().getHash())) {
            plugin.debugLog("Cached record found for " + e.player().uniqueId() + " (hash " + e.packet().getHash() + ")");
            plugin.debugLog("Hash the same, skipping installation of resource pack.");

            e.cancelled(true);

            if (plugin.getConfigManager().isFakePacketSendingEnabled()) {
                ResourcePackServerboundPacket packet = new ResourcePackServerboundPacket();
                packet.setResult(0);
                plugin.debugLog("Sending serverbound success event fake packet.");
                Protocolize.playerProvider().player(e.player().uniqueId()).sendPacketToServer(packet);
            }

            return;
        }
        plugin.getCacheManager().add(e.player().uniqueId(), e.packet().getHash());
    }

    @Override
    public void packetSend(PacketSendEvent<ResourcePackClientboundPacket> e) {
        plugin.debugLog("PacketSent: " + e.packet().toString());
    }
}

