package com.feniksovich.proxypackfix;

import java.util.*;

public class CacheManager {

    private final ProxyPackFix plugin;
    private final Map<UUID, Set<String>> cache = new HashMap<>();

    public CacheManager(ProxyPackFix plugin) {
        this.plugin = plugin;
    }

    public void add(final UUID uuid, final String hash) {
        if (cache.containsKey(uuid)) {
            cache.get(uuid).add(hash);
        } else {
            cache.put(uuid, Set.of(hash));
        }
        plugin.debugLog("Added cache record for " + uuid + " with hash " + hash);
    }

    public boolean isPresent(final UUID uuid, final String hash) {
        return cache.containsKey(uuid) && cache.get(uuid).contains(hash);
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
