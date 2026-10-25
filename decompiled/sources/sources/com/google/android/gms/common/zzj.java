package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
abstract class zzj extends com.google.android.gms.common.internal.zzw {
    private final int zza;

    public zzj(byte[] r3) {
        if (r3.length != 25) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0);
        this.zza = Arrays.hashCode(r3);
        return;
    L5:
        r0 = false;
        goto L6
    }

    public static byte[] zzf(String r1) {
        return r1.getBytes("ISO-8859-1");
    L4:
        e = move-exception;
        Buffer$$ExternalSyntheticBUOutline2.m(e);
        return null;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof com.google.android.gms.common.internal.zzx) == true) goto L17;
        return false;
    L17:
        com.google.android.gms.common.internal.zzx r42 = (com.google.android.gms.common.internal.zzx) r4;     // Catch: RemoteException -> L12
        if (r42.zze() == this.zza) goto L8;
        return false;
    L8:
        IObjectWrapper r43 = r42.zzd();     // Catch: RemoteException -> L12
        if (r43 == null) goto L14;
        byte[] r44 = (byte[]) ObjectWrapper.unwrap(r43);     // Catch: RemoteException -> L12
        return Arrays.equals(zzc(), r44);
    L14:
        return false;
    L12:
        e = move-exception;
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
        return false;
    }

    public final int hashCode() {
        return this.zza;
    }

    public abstract byte[] zzc();

    @Override // com.google.android.gms.common.internal.zzx
    public final IObjectWrapper zzd() {
        return ObjectWrapper.wrap(zzc());
    }

    @Override // com.google.android.gms.common.internal.zzx
    public final int zze() {
        return this.zza;
    }
}
