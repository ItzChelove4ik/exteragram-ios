package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;

@Deprecated
/* loaded from: classes4.dex */
public final class zbd implements Api.ApiOptions {
    public static final zbd zba = null;
    private final String zbb;
    private final boolean zbc;
    private final String zbd;

    static {
        zba = new zbd(new zbc());
    }

    public zbd(zbc r2) {
        this.zbb = null;
        this.zbc = r2.zba.booleanValue();
        this.zbd = r2.zbb;
    }

    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zbd) == true) goto L8;
        return false;
    L8:
        zbd r52 = (zbd) r5;
        if (Objects.equal(null, null) == true) goto L11;
    L15:
        return false;
    L11:
        if (this.zbc != r52.zbc) goto L15;
        if (Objects.equal(this.zbd, r52.zbd) == false) goto L15;
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{null, Boolean.valueOf(this.zbc), this.zbd});
    }

    public final Bundle zba() {
        Bundle r0 = new Bundle();
        r0.putString("consumer_package", null);
        r0.putBoolean("force_save_dialog", this.zbc);
        r0.putString("log_session_id", this.zbd);
        return r0;
    }

    public final /* synthetic */ boolean zbb() {
        return this.zbc;
    }

    public final /* synthetic */ String zbc() {
        return this.zbd;
    }
}
