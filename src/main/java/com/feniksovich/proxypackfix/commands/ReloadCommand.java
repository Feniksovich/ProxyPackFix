package com.feniksovich.proxypackfix.commands;

import com.feniksovich.proxypackfix.ProxyPackFix;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {

    private final ProxyPackFix plugin;

    public ReloadCommand(ProxyPackFix plugin) {
        super("proxypackfix", "proxypackfix.commands", "ppf");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) return;

        if (!hasPermission(sender)) {
           sender.sendMessage(TextComponent.fromLegacyText("You don't have permissions to do that.", ChatColor.DARK_RED));
           return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                plugin.getConfigManager().loadConfiguration();
                sender.sendMessage(TextComponent.fromLegacyText("Configuration successfully reloaded.", ChatColor.GREEN));
                break;
            case "clearcache":
                plugin.getCacheManager().clear();
                sender.sendMessage(TextComponent.fromLegacyText("Cache successfully cleared.", ChatColor.GREEN));
                break;
            default:
                sender.sendMessage(TextComponent.fromLegacyText("Unknown argument."));
        }
    }
}
