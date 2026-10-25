package com.google.android.gms.common.api.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;

/* loaded from: classes4.dex */
final class zabl {
    private final ApiKey zaa;
    private final Feature zab;

    public /* synthetic */ zabl(ApiKey r1, Feature r2, byte[] r3) {
        this.zaa = r1;
        this.zab = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zabl) == false) goto L10;
        zabl r42 = (zabl) r4;
        if (Objects.equal(this.zaa, r42.zaa) == false) goto L10;
        if (Objects.equal(this.zab, r42.zab) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zaa, this.zab});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(PluginsConstants.Settings.KEY, this.zaa).add("feature", this.zab).toString();
    }

    public final /* synthetic */ ApiKey zaa() {
        return this.zaa;
    }

    public final /* synthetic */ Feature zab() {
        return this.zab;
    }
}
