package com.google.firebase.remoteconfig;

import java.util.Set;

/* loaded from: classes5.dex */
public abstract class ConfigUpdate {
    public ConfigUpdate() {
    }

    public static ConfigUpdate create(Set<String> r1) {
        return new AutoValue_ConfigUpdate(r1);
    }

    public abstract Set<String> getUpdatedKeys();
}
