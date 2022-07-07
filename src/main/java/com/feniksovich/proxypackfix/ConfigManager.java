package com.feniksovich.proxypackfix;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {

    private final ProxyPackFix plugin;
    private Configuration config;

    public ConfigManager(ProxyPackFix plugin) {
        this.plugin = plugin;
    }

    public void loadConfiguration() {
        File file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                try (InputStream in = plugin.getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    plugin.getLogger().severe("Failed to create config.yml file! Read the stacktrace.");
                    e.printStackTrace();
                }
            } else {
                plugin.getLogger().warning("Failed to create required folder. It can lead to errors in further.");
            }
        }

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().severe("Failed to load config.yml file! Read the stacktrace above.");
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public boolean isDebugEnabled() {
        return config.getBoolean("debug-mode");
    }

    public boolean isFakePacketSendingEnabled() {
        return config.getBoolean("send-fake-resourcepack-status-packet");
    }
}
