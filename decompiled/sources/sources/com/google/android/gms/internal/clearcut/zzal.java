package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzal extends zzae {
    private final Object lock;
    private String zzec;
    private Object zzed;
    private final /* synthetic */ zzan zzee;

    public zzal(zzao r1, String r2, Object r3, zzan r4) {
        this.zzee = r4;
        super(r1, r2, r3, null);
        this.lock = new Object();
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final Object zza(SharedPreferences r3) {
        return zzb(r3.getString(this.zzds, _UrlKt.FRAGMENT_ENCODE_SET));
    L4:
        e = move-exception;
        String r2 = String.valueOf(this.zzds);
        if (r2.length() == 0) goto L8;
        String r22 = "Invalid byte[] value in SharedPreferences for ".concat(r2);
    L9:
        Log.e("PhenotypeFlag", r22, e);
        return null;
    L8:
        r22 = new String("Invalid byte[] value in SharedPreferences for ");
        goto L9
    }

    @Override // com.google.android.gms.internal.clearcut.zzae
    public final Object zzb(String r4) {
        Object r0 = this.lock;     // Catch: Throwable -> L14
        monitor-enter(r0);     // Catch: Throwable -> L14
    L7:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14 Throwable -> L14
    L5:
        if (r4.equals(this.zzec) == true) goto L9;
        Object r1 = this.zzee.zzb(Base64.decode(r4, 3));     // Catch: Throwable -> L7
        this.zzec = r4;     // Catch: Throwable -> L7
        this.zzed = r1;     // Catch: Throwable -> L7
    L9:
        Object r12 = this.zzed;     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r12;
    L14:
        String r3 = this.zzds;
        StringBuilder r2 = new StringBuilder((String.valueOf(r3).length() + 27) + String.valueOf(r4).length());
        r2.append("Invalid byte[] value for ");
        r2.append(r3);
        r2.append(": ");
        r2.append(r4);
        Log.e("PhenotypeFlag", r2.toString());
        return null;
    }
}
