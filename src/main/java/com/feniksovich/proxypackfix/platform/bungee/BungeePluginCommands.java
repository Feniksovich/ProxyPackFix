package com.feniksovich.proxypackfix.platform.bungee;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class BungeePluginCommands extends Command {

    private final BungeeProxyPackFix plugin;

    public BungeePluginCommands(BungeeProxyPackFix plugin) {
        super("proxypackfix", "proxypackfix.commands", "ppf");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) return;

        if (!hasPermission(sender)) {
           sender.sendMessage(TextComponent.fromLegacyText("§4You don't have permissions to do that."));
           return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.getConfigManager().loadConfiguration();
                sender.sendMessage(TextComponent.fromLegacyText("§aConfiguration successfully reloaded.", ChatColor.GREEN));
                break;
            case "clearcache":
                plugin.getCacheManager().clear();
                sender.sendMessage(TextComponent.fromLegacyText("§aCache successfully cleared.", ChatColor.GREEN));
                break;
            default:
                sender.sendMessage(TextComponent.fromLegacyText("§7Unknown argument."));
        }
    }
}
