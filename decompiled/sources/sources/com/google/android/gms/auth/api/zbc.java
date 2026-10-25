package com.google.android.gms.auth.api;

@Deprecated
/* loaded from: classes4.dex */
public final class zbc {
    protected Boolean zba;
    protected String zbb;

    public zbc() {
        this.zba = Boolean.FALSE;
    }

    public zbc(zbd r2) {
        this.zba = Boolean.FALSE;
        this.zba = Boolean.valueOf(r2.zbb());
        this.zbb = r2.zbc();
    }

    public final zbc zba(String r1) {
        this.zbb = r1;
        return this;
    }
}
