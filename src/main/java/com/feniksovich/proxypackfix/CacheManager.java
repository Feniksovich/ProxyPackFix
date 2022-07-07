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
        plugin.getLogger().warning("Added cache record for " + uuid + " with hash " + hash);
        cache.put(uuid, hash);
    }

    public boolean isSame(final UUID uuid, final String hash) {
        return cache.containsKey(uuid) && cache.get(uuid).equals(hash);
    }

    public void remove(final UUID uuid) {
        plugin.getLogger().warning("Removed cache record for " + uuid);
        cache.remove(uuid);
    }
}
