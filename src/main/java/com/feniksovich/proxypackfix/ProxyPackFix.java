package com.feniksovich.proxypackfix;

import com.feniksovich.proxypackfix.common.protocol.listeners.ResourcePackClientboundPacketListener;
import com.feniksovich.proxypackfix.common.protocol.listeners.ResourcePackServerboundPacketListener;
import com.feniksovich.proxypackfix.common.protocol.packets.ResourcePackClientboundPacket;
import com.feniksovich.proxypackfix.common.protocol.packets.ResourcePackServerboundPacket;
import dev.simplix.protocolize.api.PacketDirection;
import dev.simplix.protocolize.api.Protocol;
import dev.simplix.protocolize.api.Protocolize;

import java.util.logging.Logger;

public interface ProxyPackFix {

    CacheManager getCacheManager();
    ConfigManager getConfigManager();

    Logger getLogger();

    String getCanonicalPlatformName();
    String getPlatformVersion();
    String getPluginVersion();

    default void greetings() {
        this.getLogger().info("");
        this.getLogger().info("§6ProxyPackFix §fv" + getPluginVersion());
        this.getLogger().info("§6Platform: §f" + getCanonicalPlatformName());
        this.getLogger().info("§6Version:  §f" + getPlatformVersion());
        this.getLogger().info("§6Developer: §fFeniksovich (feniksovich.github.io)");
        this.getLogger().info("");
    }

    default void registerPackets() {
        this.getLogger().info("Registering required packets and listeners...");
        Protocolize.protocolRegistration().registerPacket(ResourcePackClientboundPacket.MAPPINGS, Protocol.PLAY, PacketDirection.CLIENTBOUND, ResourcePackClientboundPacket.class);
        Protocolize.protocolRegistration().registerPacket(ResourcePackServerboundPacket.MAPPINGS, Protocol.PLAY, PacketDirection.SERVERBOUND, ResourcePackServerboundPacket.class);

        Protocolize.listenerProvider().registerListener(new ResourcePackClientboundPacketListener(this));
        Protocolize.listenerProvider().registerListener(new ResourcePackServerboundPacketListener(this));
    }

    default void debugLog(String msg) {
        if (getConfigManager().isDebugEnabled()) {
            this.getLogger().info("# " + msg);
        }
    }
}
