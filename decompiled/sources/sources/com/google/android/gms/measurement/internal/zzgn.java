package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzgn {
    protected static final AtomicReference zza = null;
    protected static final AtomicReference zzb = null;
    protected static final AtomicReference zzc = null;
    private final zzgm zzd;

    static {
        zza = new AtomicReference();
        zzb = new AtomicReference();
        zzc = new AtomicReference();
    }

    public zzgn(zzgm r1) {
        this.zzd = r1;
    }

    private static final String zzg(String r3, String[] r4, String[] r5, AtomicReference r6) {
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r5);
        Preconditions.checkNotNull(r6);
        int r2 = 0;
        if (r4.length != r5.length) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
    L8:
        if (r2 >= r4.length) goto L25;
        if (Objects.equals(r3, r4[r2]) == true) goto L11;
        r2 = r2 + 1;
        goto L8
    L11:
        monitor-enter(r6);
        String[] r32 = (String[]) r6.get();     // Catch: Throwable -> L15
        if (r32 != null) goto L17;
        r32 = new String[r5.length];     // Catch: Throwable -> L15
        r6.set(r32);     // Catch: Throwable -> L15
    L17:
        String r02 = r32[r2];     // Catch: Throwable -> L15
        if (r02 != null) goto L20;
        r02 = r5[r2] + "(" + r4[r2] + ")";     // Catch: Throwable -> L15
        r32[r2] = r02;     // Catch: Throwable -> L15
    L20:
        monitor-exit(r6);     // Catch: Throwable -> L15
        return r02;
    L15:
        th = move-exception;
        throw th;
    L25:
        return r3;
    L5:
        r0 = false;
        goto L6
    }

    public final String zza(String r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (this.zzd.zza() == true) goto L9;
        return r3;
    L9:
        return zzg(r3, zzjm.zzf, zzjm.zza, zza);
    }

    public final String zzb(String r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (this.zzd.zza() == true) goto L9;
        return r3;
    L9:
        return zzg(r3, zzjn.zzb, zzjn.zza, zzb);
    }

    public final String zzc(String r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (this.zzd.zza() == true) goto L9;
        return r3;
    L9:
        if (r3.startsWith("_exp_") == false) goto L13;
        return "experiment_id(" + r3 + ")";
    L13:
        return zzg(r3, zzjo.zzb, zzjo.zza, zzc);
    }

    public final String zzd(zzbh r4) {
        zzgm r0 = this.zzd;
        if (r0.zza() == false) goto L5;
        StringBuilder r1 = new StringBuilder("origin=");
        r1.append(r4.zzc);
        r1.append(",name=");
        r1.append(zza(r4.zza));
        r1.append(",params=");
        zzbf r42 = r4.zzb;
        if (r42 != null) goto L10;
        String r3 = null;
    L13:
        r1.append(r3);
        return r1.toString();
    L10:
        if (r0.zza() == true) goto L12;
        r3 = r42.toString();
        goto L13
    L12:
        r3 = zze(r42.zzf());
        goto L13
    L5:
        return r4.toString();
    }

    public final String zze(Bundle r6) {
        if (r6 != null) goto L6;
        return null;
    L6:
        if (this.zzd.zza() == false) goto L8;
        StringBuilder r0 = new StringBuilder();
        r0.append("Bundle[{");
        Iterator<String> r1 = r6.keySet().iterator();
    L11:
        if (r1.hasNext() == false) goto L26;
        String r2 = r1.next();
        if (r0.length() == 8) goto L15;
        r0.append(", ");
    L15:
        r0.append(zzb(r2));
        r0.append("=");
        Object r22 = r6.get(r2);
        if ((r22 instanceof Bundle) == false) goto L19;
        String r23 = zzf(new Object[]{r22});
    L25:
        r0.append(r23);
        goto L11
    L19:
        if ((r22 instanceof Object[]) == false) goto L22;
        r23 = zzf((Object[]) r22);
        goto L25
    L22:
        if ((r22 instanceof ArrayList) == false) goto L24;
        r23 = zzf(((ArrayList) r22).toArray());
        goto L25
    L24:
        r23 = String.valueOf(r22);
        goto L25
    L26:
        r0.append("}]");
        return r0.toString();
    L8:
        return r6.toString();
    }

    public final String zzf(Object[] r6) {
        if (r6 != null) goto L5;
        return _UrlKt.PATH_SEGMENT_ENCODE_SET_URI;
    L5:
        StringBuilder r0 = new StringBuilder();
        r0.append("[");
        int r1 = 0;
    L7:
        if (r1 >= r6.length) goto L18;
        Object r2 = r6[r1];
        if ((r2 instanceof Bundle) == false) goto L11;
        String r22 = zze((Bundle) r2);
    L12:
        if (r22 == null) goto L17;
        if (r0.length() == 1) goto L16;
        r0.append(", ");
    L16:
        r0.append(r22);
    L17:
        r1 = r1 + 1;
        goto L7
    L11:
        r22 = String.valueOf(r2);
        goto L12
    L18:
        r0.append("]");
        return r0.toString();
    }
}
