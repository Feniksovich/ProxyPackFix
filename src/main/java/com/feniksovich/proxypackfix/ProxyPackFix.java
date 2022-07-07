package com.feniksovich.proxypackfix;

import com.feniksovich.proxypackfix.listeners.PlayerDisconnectListener;
import com.feniksovich.proxypackfix.listeners.ResourcePackClientboundPacketListener;
import com.feniksovich.proxypackfix.listeners.ResourcePackServerboundPacketListener;
import com.feniksovich.proxypackfix.packets.ResourcePackServerboundPacket;
import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;
import net.md_5.bungee.api.plugin.Plugin;
import com.feniksovich.proxypackfix.packets.ResourcePackClientboundPacket;

public class ProxyPackFix extends Plugin {

    private CacheManager cacheManager;

    @Override
    public void onEnable() {
        cacheManager = new CacheManager(this);

        Protocolize.protocolRegistration().registerPacket(ResourcePackClientboundPacket.MAPPINGS, Protocol.PLAY, PacketDirection.CLIENTBOUND, ResourcePackClientboundPacket.class);
        Protocolize.protocolRegistration().registerPacket(ResourcePackServerboundPacket.MAPPINGS, Protocol.PLAY, PacketDirection.SERVERBOUND, ResourcePackServerboundPacket.class);

        Protocolize.listenerProvider().registerListener(new ResourcePackClientboundPacketListener(this));
        Protocolize.listenerProvider().registerListener(new ResourcePackServerboundPacketListener(this));

        getProxy().getPluginManager().registerListener(this, new PlayerDisconnectListener(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }
}
