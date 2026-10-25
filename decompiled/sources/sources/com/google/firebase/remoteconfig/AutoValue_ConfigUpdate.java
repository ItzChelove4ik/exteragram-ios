package com.google.firebase.remoteconfig;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Set;

/* loaded from: classes5.dex */
final class AutoValue_ConfigUpdate extends ConfigUpdate {
    private final Set<String> updatedKeys;

    public AutoValue_ConfigUpdate(Set<String> r1) {
        if (r1 == null) goto L6;
        this.updatedKeys = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null updatedKeys");
        throw null;
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof ConfigUpdate) == true) goto L8;
        return false;
    L8:
        return this.updatedKeys.equals(((ConfigUpdate) r2).getUpdatedKeys());
    }

    @Override // com.google.firebase.remoteconfig.ConfigUpdate
    public Set<String> getUpdatedKeys() {
        return this.updatedKeys;
    }

    public int hashCode() {
        return this.updatedKeys.hashCode() ^ 1000003;
    }

    public String toString() {
        return "ConfigUpdate{updatedKeys=" + this.updatedKeys + "}";
    }
}
