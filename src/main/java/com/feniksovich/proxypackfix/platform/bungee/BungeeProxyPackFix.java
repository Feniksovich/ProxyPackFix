package com.feniksovich.proxypackfix.platform.bungee;

import com.feniksovich.proxypackfix.CacheManager;
import com.feniksovich.proxypackfix.ConfigManager;
import com.feniksovich.proxypackfix.ProxyPackFix;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Logger;

public class BungeeProxyPackFix extends Plugin implements ProxyPackFix {

    private ConfigManager configManager;
    private CacheManager cacheManager;

    @Override
    public void onEnable() {
        greetings();

        configManager = new BungeeConfigManager(this);
        configManager.loadConfiguration();

        cacheManager = new CacheManager(this);

        registerPackets();

        // Register BungeeCord specific listeners & command handlers
        getProxy().getPluginManager().registerListener(this, new BungeeDisconnectListener(this));
        getProxy().getPluginManager().registerCommand(this, new BungeePluginCommands(this));

    }

    @Override
    public CacheManager getCacheManager() {
        return cacheManager;
    }

    @Override
    public ConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public Logger getLogger() {
        return getProxy().getLogger();
    }

    @Override
    public String getCanonicalPlatformName() {
        if (getProxy().getVersion().contains("BungeeCord")) return "BungeeCord";
        if (getProxy().getVersion().contains("Waterfall")) return "Waterfall";
        return "BungeeCord fork";
    }

    @Override
    public String getPlatformVersion() {
        return getProxy().getVersion();
    }

    @Override
    public String getPluginVersion() {
        return this.getDescription().getVersion();
    }
}
