package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;

/* loaded from: classes4.dex */
public abstract class zzba extends zzl implements zzbb {
    public zzba() {
        super("com.google.android.apps.aicore.aidl.ICortanaStateCallback");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzl
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L6;
        ParcelFileDescriptor r12 = (ParcelFileDescriptor) zzm.zza(r2, ParcelFileDescriptor.CREATOR);
        zzm.zzc(r2);
        zzd(r12);
        return true;
    L6:
        return false;
    }
}
