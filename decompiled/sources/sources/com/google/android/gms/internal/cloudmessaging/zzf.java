package com.google.android.gms.internal.cloudmessaging;

import android.os.BadParcelableException;
import android.os.Parcel;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
public abstract class zzf extends zzb implements zzg {
    public zzf() {
        super("com.google.android.gms.cloudmessaging.internal.IRegisterCallback");
    }

    @Override // com.google.android.gms.internal.cloudmessaging.zzb
    public final boolean zza(int r2, Parcel r3, Parcel r4, int r5) {
        if (r2 != 1) goto L10;
        Status r22 = (Status) zzc.zza(r3, Status.CREATOR);
        String r52 = r3.readString();
        ApiMetadata r0 = (ApiMetadata) zzc.zza(r3, ApiMetadata.CREATOR);
        int r32 = r3.dataAvail();
        if (r32 > 0) goto L8;
        zzb(r22, r52, r0);
        return true;
    L8:
        StringBuilder r42 = new StringBuilder(String.valueOf(r32).length() + 45);
        r42.append("Parcel data not fully consumed, unread size: ");
        r42.append(r32);
        throw new BadParcelableException(r42.toString());
    L10:
        return false;
    }
}
