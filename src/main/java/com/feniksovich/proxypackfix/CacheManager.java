package com.feniksovich.proxypackfix;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CacheManager {

    private final ProxyPackFix plugin;
    private final Map<UUID, String> cache = new HashMap<>();

    public CacheManager(ProxyPackFix plugin) {
        this.plugin = plugin;
    }

    public void add(final UUID uuid, final String hash) {
        cache.put(uuid, hash);
        plugin.debugLog("Added cache record for " + uuid + " with hash " + hash);
    }

    public boolean isPresent(final UUID uuid, final String hash) {
        return cache.containsKey(uuid) && cache.get(uuid).equals(hash);
    }

    public void remove(final UUID uuid) {
        cache.remove(uuid);
        plugin.debugLog("Removed cache record for " + uuid + " (user disconnected)");
    }

    public void clear() {
        cache.clear();
        plugin.debugLog("Cleared cached player list.");
    }
}
