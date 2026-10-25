package com.google.android.gms.cast.internal;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class Logger {
    protected final String zza;
    private final boolean zzb;
    private final String zzc;
    private boolean zzd;

    public Logger(String r2) {
        this(r2, null);
    }

    public Logger(String r2, String r3) {
        Preconditions.checkNotEmpty(r2, "The log tag cannot be null or empty.");
        this.zza = r2;
        this.zzc = r3;
        if (r2.length() > 23) goto L5;
        boolean r22 = true;
    L6:
        this.zzb = r22;
        this.zzd = false;
        return;
    L5:
        r22 = false;
        goto L6
    }

    private static final boolean zzd() {
        if (Build.TYPE.equals("user") == true) goto L6;
        return true;
    L6:
        return false;
    }

    public void d(String r2, Object... r3) {
        if (zza() == true) goto L5;
        return;
    L5:
        Log.d(this.zza, zzc(r2, r3));
    }

    public void d(Throwable r2, String r3, Object... r4) {
        if (zza() == true) goto L5;
        return;
    L5:
        Log.d(this.zza, zzc(r3, r4), r2);
    }

    public void e(String r2, Object... r3) {
        Log.e(this.zza, zzc(r2, r3));
    }

    public void e(Throwable r2, String r3, Object... r4) {
        Log.e(this.zza, zzc(r3, r4), r2);
    }

    public void i(String r2, Object... r3) {
        Log.i(this.zza, zzc(r2, r3));
    }

    public void i(Throwable r2, String r3, Object... r4) {
        Log.i(this.zza, zzc(r3, r4), r2);
    }

    public void v(String r3, Object... r4) {
        if (zzd() == true) goto L5;
        return;
    L5:
        if (this.zzb == false) goto L11;
        String r0 = this.zza;
        if (Log.isLoggable(r0, 2) == false) goto L12;
        Log.v(r0, zzc(r3, r4));
        return;
    L12:
        return;
    }

    public void w(String r2, Object... r3) {
        Log.w(this.zza, zzc(r2, r3));
    }

    public void w(Throwable r2, String r3, Object... r4) {
        Log.w(this.zza, zzc(r3, r4), r2);
    }

    public final boolean zza() {
        if (zzd() == true) goto L5;
        return false;
    L5:
        if (this.zzd == false) goto L7;
        return true;
    L7:
        if (this.zzb == true) goto L9;
        return false;
    L9:
        if (Log.isLoggable(this.zza, 3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public final void zzb(boolean r1) {
        this.zzd = true;
    }

    public final String zzc(String r2, Object... r3) {
        if (r3.length == 0) goto L5;
        r2 = String.format(Locale.ROOT, r2, r3);
    L5:
        String r1 = this.zzc;
        if (TextUtils.isEmpty(r1) == false) goto L8;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        if (TextUtils.isEmpty(r12) == false) goto L12;
        return r2;
    L12:
        return r12.concat(String.valueOf(r2));
    L8:
        r12 = String.format("[%s] ", new Object[]{r1});
        goto L10
    }
}
