package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes4.dex */
public final class zzt extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzt> CREATOR = null;
    private final String zza;
    private final zzj zzb;
    private final boolean zzc;
    private final boolean zzd;

    static {
        CREATOR = new zzu();
    }

    public zzt(String r3, IBinder r4, boolean r5, boolean r6) {
        this.zza = r3;
        zzk r32 = null;
        if (r4 != null) goto L16;
    L14:
        this.zzb = r32;
        this.zzc = r5;
        this.zzd = r6;
        return;
    L16:
        IObjectWrapper r42 = com.google.android.gms.common.internal.zzw.zzg(r4).zzd();     // Catch: RemoteException -> L12
        if (r42 != null) goto L8;
        byte[] r43 = null;
    L9:
        if (r43 == null) goto L11;
        r32 = new zzk(r43);
        goto L14
    L11:
        Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
        goto L14
    L8:
        r43 = (byte[]) ObjectWrapper.unwrap(r42);
    L12:
        e = move-exception;
        Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e);
        goto L14
    }

    public zzt(String r1, zzj r2, boolean r3, boolean r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zza;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        zzj r53 = this.zzb;
        if (r53 != null) goto L5;
        Log.w("GoogleCertificatesQuery", "certificate binder is null");
        r53 = null;
    L5:
        SafeParcelWriter.writeIBinder(r4, 2, r53, false);
        SafeParcelWriter.writeBoolean(r4, 3, this.zzc);
        SafeParcelWriter.writeBoolean(r4, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
