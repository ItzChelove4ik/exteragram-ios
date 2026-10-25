package com.google.android.gms.common.logging;

import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class Logger {
    private final String zza;
    private final String zzb;
    private final GmsLogger zzc;
    private final int zzd;

    public Logger(String r7, String... r8) {
        int r0 = r8.length;
        if (r0 != 0) goto L5;
        String r82 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        this.zzb = r82;
        this.zza = r7;
        this.zzc = new GmsLogger(r7);
        int r72 = 2;
    L14:
        if (r72 > 7) goto L18;
        if (Log.isLoggable(this.zza, r72) == true) goto L18;
        r72 = r72 + 1;
    L18:
        this.zzd = r72;
        return;
    L5:
        StringBuilder r1 = new StringBuilder();
        r1.append('[');
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L11;
        String r3 = r8[r2];
        if (r1.length() <= 1) goto L10;
        r1.append(",");
    L10:
        r1.append(r3);
        r2 = r2 + 1;
        goto L6
    L11:
        r1.append("] ");
        r82 = r1.toString();
        goto L12
    }

    public void d(String r2, Object... r3) {
        if (isLoggable(3) == false) goto L6;
        Log.d(this.zza, format(r2, r3));
        return;
    }

    public void e(String r2, Object... r3) {
        Log.e(this.zza, format(r2, r3));
    }

    public String format(String r2, Object... r3) {
        if (r3 == null) goto L7;
        if (r3.length <= 0) goto L7;
        r2 = String.format(Locale.US, r2, r3);
    L7:
        return this.zzb.concat(r2);
    }

    public boolean isLoggable(int r1) {
        if (this.zzd > r1) goto L6;
        return true;
    L6:
        return false;
    }
}
