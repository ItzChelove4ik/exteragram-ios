package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.location.zzv;

@Deprecated
/* loaded from: classes4.dex */
public final class zzeh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzeh> CREATOR = null;
    private final int zza;
    private final zzef zzb;
    private final com.google.android.gms.location.zzy zzc;
    private final zzv zzd;
    private final PendingIntent zze;
    private final zzq zzf;
    private final String zzg;

    static {
        CREATOR = new zzei();
    }

    public zzeh(int r1, zzef r2, IBinder r3, IBinder r4, PendingIntent r5, IBinder r6, String r7) {
        this.zza = r1;
        this.zzb = r2;
        zzq r12 = null;
        if (r3 == null) goto L5;
        com.google.android.gms.location.zzy r22 = com.google.android.gms.location.zzx.zzb(r3);
    L6:
        this.zzc = r22;
        this.zze = r5;
        if (r4 == null) goto L9;
        zzv r23 = com.google.android.gms.location.zzu.zzb(r4);
    L10:
        this.zzd = r23;
        if (r6 == null) goto L16;
        IInterface r13 = r6.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
        if ((r13 instanceof zzq) == false) goto L15;
        r12 = (zzq) r13;
        goto L16
    L15:
        r12 = new zzo(r6);
    L16:
        this.zzf = r12;
        this.zzg = r7;
        return;
    L9:
        r23 = null;
        goto L10
    L5:
        r22 = null;
        goto L6
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r6, int r7) {
        int r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeInt(r6, 1, r0);
        SafeParcelWriter.writeParcelable(r6, 2, this.zzb, r7, false);
        com.google.android.gms.location.zzy r02 = this.zzc;
        IBinder r2 = null;
        if (r02 != null) goto L5;
        IBinder r03 = null;
    L6:
        SafeParcelWriter.writeIBinder(r6, 3, r03, false);
        SafeParcelWriter.writeParcelable(r6, 4, this.zze, r7, false);
        zzv r72 = this.zzd;
        if (r72 != null) goto L9;
        IBinder r73 = null;
    L10:
        SafeParcelWriter.writeIBinder(r6, 5, r73, false);
        zzq r74 = this.zzf;
        if (r74 == null) goto L14;
        r2 = r74.asBinder();
    L14:
        SafeParcelWriter.writeIBinder(r6, 6, r2, false);
        SafeParcelWriter.writeString(r6, 8, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(r6, r1);
        return;
    L9:
        r73 = r72.asBinder();
        goto L10
    L5:
        r03 = r02.asBinder();
        goto L6
    }
}
