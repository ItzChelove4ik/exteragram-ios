package com.google.android.gms.auth.api.signin.internal;

/* loaded from: classes4.dex */
public class HashAccumulator {
    private int zaa;

    public HashAccumulator() {
        this.zaa = 1;
    }

    public HashAccumulator addObject(Object r2) {
        int r0 = this.zaa * 31;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        this.zaa = r0 + r22;
        return this;
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public int hash() {
        return this.zaa;
    }

    public final HashAccumulator zaa(boolean r2) {
        this.zaa = (this.zaa * 31) + (r2 ? 1 : 0);
        return this;
    }
}
