package com.google.android.gms.common.internal;

import android.util.Log;

/* loaded from: classes4.dex */
public final class GmsLogger {
    private final String zza;
    private final String zzb;

    public GmsLogger(String r2) {
        this(r2, null);
    }

    public GmsLogger(String r4, String r5) {
        Preconditions.checkNotNull(r4, "log tag cannot be null");
        Object[] r2 = {r4, 23};
        if (r4.length() > 23) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "tag \"%s\" is longer than the %d character maximum", r2);
        this.zza = r4;
        if (r5 != null) goto L9;
    L10:
        r5 = null;
    L11:
        this.zzb = r5;
        return;
    L9:
        if (r5.length() > 0) goto L11;
    L5:
        r0 = false;
        goto L6
    }

    private final String zza(String r1) {
        String r0 = this.zzb;
        if (r0 != null) goto L6;
        return r1;
    L6:
        return r0.concat(r1);
    }

    public boolean canLog(int r1) {
        return Log.isLoggable(this.zza, r1);
    }

    public void d(String r2, String r3) {
        if (canLog(3) == false) goto L6;
        Log.d(r2, zza(r3));
        return;
    }

    public void e(String r2, String r3) {
        if (canLog(6) == false) goto L6;
        Log.e(r2, zza(r3));
        return;
    }

    public void e(String r2, String r3, Throwable r4) {
        if (canLog(6) == false) goto L6;
        Log.e(r2, zza(r3), r4);
        return;
    }

    public void v(String r2, String r3) {
        if (canLog(2) == false) goto L6;
        Log.v(r2, zza(r3));
        return;
    }

    public void w(String r2, String r3) {
        if (canLog(5) == false) goto L6;
        Log.w(r2, zza(r3));
        return;
    }
}
