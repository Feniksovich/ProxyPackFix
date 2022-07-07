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
        plugin.getLogger().warning("PacketReceive: " + e.packet().toString());
        if (plugin.getCacheManager().isSame(e.player().uniqueId(), e.packet().getHash())) {
            plugin.getLogger().warning("Hash the same, skipping installation of resourcepack and sending serverbound success event packet.");

            e.cancelled(true);

            ResourcePackServerboundPacket packet = new ResourcePackServerboundPacket();
            packet.setResult(0);
            Protocolize.playerProvider().player(e.player().uniqueId()).sendPacketToServer(packet);
            return;
        }
        plugin.getCacheManager().add(e.player().uniqueId(), e.packet().getHash());
    }

    @Override
    public void packetSend(PacketSendEvent<ResourcePackClientboundPacket> e) {
        plugin.getLogger().warning("PacketSend: " + e.packet().toString());
    }
}

