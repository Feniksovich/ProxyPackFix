package com.feniksovich.proxypackfix;

public abstract class ConfigManager {
    public abstract void loadConfiguration();
    public abstract boolean isDebugEnabled();
    public abstract boolean isFakePacketSendingEnabled();
}
