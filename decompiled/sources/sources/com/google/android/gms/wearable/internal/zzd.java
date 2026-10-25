package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class zzd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzd> CREATOR = null;
    public final zzfa zza;
    public final IntentFilter[] zzb;
    public final String zzc;
    public final String zzd;

    static {
        CREATOR = new zze();
    }

    public zzd(IBinder r3, IntentFilter[] r4, String r5, String r6) {
        if (r3 == null) goto L9;
        IInterface r0 = r3.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
        if ((r0 instanceof zzfa) == false) goto L7;
        zzfa r02 = (zzfa) r0;
    L8:
        this.zza = r02;
    L10:
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r6;
        return;
    L7:
        r02 = new zzey(r3);
        goto L8
    L9:
        this.zza = null;
        goto L10
    }

    public zzd(zzit r1) {
        this.zza = r1;
        throw null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        zzfa r1 = this.zza;
        if (r1 != null) goto L5;
        IBinder r12 = null;
    L6:
        SafeParcelWriter.writeIBinder(r5, 2, r12, false);
        SafeParcelWriter.writeTypedArray(r5, 3, this.zzb, r6, false);
        SafeParcelWriter.writeString(r5, 4, this.zzc, false);
        SafeParcelWriter.writeString(r5, 5, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
        return;
    L5:
        r12 = r1.asBinder();
        goto L6
    }
}
