package com.feniksovich.proxypackfix.platform.velocity;

import com.feniksovich.proxypackfix.ConfigManager;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;
import org.yaml.snakeyaml.DumperOptions;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class VelocityConfigManager extends ConfigManager {

    private final VelocityProxyPackFix plugin;
    private final Path path;

    private ConfigurationNode config;

    public VelocityConfigManager(VelocityProxyPackFix plugin, Path path) {
        this.plugin = plugin;
        this.path = path;
    }

    @Override
    public void loadConfiguration() {
        File file = new File(String.valueOf(path), "config.yml");

        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.yml")) {
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
            config = YAMLConfigurationLoader.builder()
                    .setIndent(2)
                    .setPath(file.toPath())
                    .setFlowStyle(DumperOptions.FlowStyle.BLOCK)
                    .build()
                    .load();
        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().severe("Failed to load config.yml file! Read the stacktrace above.");
        }
    }

    @Override
    public boolean isDebugEnabled() {
        return config.getNode("debug-enabled").getBoolean();
    }

    @Override
    public boolean isFakePacketSendingEnabled() {
        return config.getNode("send-fake-resourcepack-status-packet").getBoolean();
    }
}
