package com.feniksovich.proxypackfix.common.protocol.listeners;

import com.feniksovich.proxypackfix.common.protocol.packets.ResourcePackServerboundPacket;
import com.feniksovich.proxypackfix.ProxyPackFix;
import dev.simplix.protocolize.api.Direction;
import dev.simplix.protocolize.api.listener.AbstractPacketListener;
import dev.simplix.protocolize.api.listener.PacketReceiveEvent;
import dev.simplix.protocolize.api.listener.PacketSendEvent;

public class ResourcePackServerboundPacketListener extends AbstractPacketListener<ResourcePackServerboundPacket> {

    private final ProxyPackFix plugin;

    public ResourcePackServerboundPacketListener(ProxyPackFix plugin) {
        super(ResourcePackServerboundPacket.class, Direction.DOWNSTREAM, 1);
        this.plugin = plugin;
    }

    @Override
    public void packetReceive(PacketReceiveEvent<ResourcePackServerboundPacket> e) {
        plugin.debugLog("PacketReceived: " + e.packet().toString());
    }

    @Override
    public void packetSend(PacketSendEvent<ResourcePackServerboundPacket> e) {
        plugin.debugLog("PacketSent: " + e.packet().toString());
    }
}
