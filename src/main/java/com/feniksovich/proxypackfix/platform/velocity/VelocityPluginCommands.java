package com.feniksovich.proxypackfix.platform.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class VelocityPluginCommands implements SimpleCommand {

    private final VelocityProxyPackFix plugin;

    public VelocityPluginCommands(VelocityProxyPackFix plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final Invocation i) {
        CommandSource source = i.source();

        if (!hasPermission(i)) {
            source.sendMessage(Component.text("You don't have permissions to do that.").color(NamedTextColor.DARK_RED));
            return;
        }

        String[] args = i.arguments();
        if (args.length == 0) return;

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.getConfigManager().loadConfiguration();
                source.sendMessage(Component.text("Configuration successfully reloaded.").color(NamedTextColor.GREEN));
                break;
            case "clearcache":
                plugin.getCacheManager().clear();
                source.sendMessage(Component.text("Cache successfully cleared.").color(NamedTextColor.GREEN));
                break;
            default:
                source.sendMessage(Component.text("Unknown argument.").color(NamedTextColor.GRAY));
        }
    }

    @Override
    public boolean hasPermission(final Invocation i) {
        return i.source().hasPermission("proxypackfix.commands");
    }
}
