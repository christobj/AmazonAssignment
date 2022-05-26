package com.assignment.config.factory;

import com.assignment.config.CoreConfig;
import org.aeonbits.owner.ConfigCache;

public final class CoreConfigFactory {

    private CoreConfigFactory() {}

    public static CoreConfig getConfig() {
        return ConfigCache.getOrCreate(CoreConfig.class);
    }
}
