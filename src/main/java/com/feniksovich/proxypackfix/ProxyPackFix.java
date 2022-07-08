package com.feniksovich.proxypackfix;

import com.feniksovich.proxypackfix.commands.ReloadCommand;
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

    private ConfigManager configManager;
    private CacheManager cacheManager;

    @Override
    public void onEnable() {
        greetings();

        configManager = new ConfigManager(this);
        configManager.loadConfiguration();

        cacheManager = new CacheManager(this);

        getLogger().info("Registering required packets and listeners...");
        Protocolize.protocolRegistration().registerPacket(ResourcePackClientboundPacket.MAPPINGS, Protocol.PLAY, PacketDirection.CLIENTBOUND, ResourcePackClientboundPacket.class);
        Protocolize.protocolRegistration().registerPacket(ResourcePackServerboundPacket.MAPPINGS, Protocol.PLAY, PacketDirection.SERVERBOUND, ResourcePackServerboundPacket.class);

        Protocolize.listenerProvider().registerListener(new ResourcePackClientboundPacketListener(this));
        Protocolize.listenerProvider().registerListener(new ResourcePackServerboundPacketListener(this));

        getProxy().getPluginManager().registerListener(this, new PlayerDisconnectListener(this));
        getProxy().getPluginManager().registerCommand(this, new ReloadCommand(this));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void greetings() {
        getLogger().info("");
        getLogger().info("§6ProxyPackFix §fv" + this.getDescription().getVersion());
        getLogger().info("§6Platform: §f" + this.getProxy().getVersion());
        getLogger().info("§6Developer: §fFeniksovich (feniksovich.github.io)");
        getLogger().info("");
    }

    public void debugLog(String msg) {
        if (configManager.isDebugEnabled()) getLogger().info("# " + msg);
    }

    public ConfigManager getConfigManager() { return configManager; }

    public CacheManager getCacheManager() {
        return cacheManager;
    }
}
