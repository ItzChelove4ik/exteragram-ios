package com.google.android.gms.internal.icing;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zzab extends zzb implements zzac {
    public zzab() {
        super("com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearchCallbacks");
    }

    @Override // com.google.android.gms.internal.icing.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        zzb((Status) zzc.zza(r2, Status.CREATOR));
    L13:
        return true;
    L5:
        if (r1 != 2) goto L7;
        Status r0 = (Status) zzc.zza(r2, Status.CREATOR);
        ParcelFileDescriptor r02 = (ParcelFileDescriptor) zzc.zza(r2, ParcelFileDescriptor.CREATOR);
        goto L13
    L7:
        if (r1 == 4) goto L10;
        return false;
    L10:
        zzo r03 = (zzo) zzc.zza(r2, zzo.CREATOR);
        goto L13
    }
}
