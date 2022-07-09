package com.feniksovich.proxypackfix.platform.velocity;

import com.feniksovich.proxypackfix.CacheManager;
import com.feniksovich.proxypackfix.ConfigManager;
import com.feniksovich.proxypackfix.ProxyPackFix;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "proxypackfix", name = "ProxyPackFix", version = "2.0.0",
        url = "https://feniksovich.github.io", authors = "Feniksovich",
        dependencies = {@Dependency(id = "protocolize")})
public class VelocityProxyPackFix implements ProxyPackFix {

    private final ProxyServer server;
    private final Logger logger;
    private final Path path;

    private ConfigManager configManager;
    private CacheManager cacheManager;

    @Inject
    public VelocityProxyPackFix(ProxyServer server, Logger logger, @DataDirectory Path path) {
        this.server = server;
        this.logger = logger;

        // Velocity provides folder name in lower case for some reason.
        // Changes "proxypackfix" to "ProxyPackFix".
        this.path = Path.of(String.valueOf(path).replace("proxypackfix", "ProxyPackFix"));
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent e) {
        greetings();

        configManager = new VelocityConfigManager(this, path);
        configManager.loadConfiguration();

        cacheManager = new CacheManager(this);

        registerPackets();

        // Register Velocity specific listeners & command handlers
        server.getEventManager().register(this, new VelocityDisconnectListener(this));
        server.getCommandManager().register(
                server.getCommandManager().metaBuilder("proxypackfix").aliases("ppf").build(),
                new VelocityPluginCommands(this));
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
        return logger;
    }

    @Override
    public String getCanonicalPlatformName() {
        return "Velocity";
    }

    @Override
    public String getPlatformVersion() {
        return server.getVersion().getVersion();
    }

    @Override
    public String getPluginVersion() {
        return "2.0.0";
    }
}
