package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzar extends zzl implements zzas {
    public zzar() {
        super("com.google.android.apps.aicore.aidl.IAiCoreServiceProviderCallback");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzl
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L5;
        IBinder r12 = r2.readStrongBinder();
        if (r12 != null) goto L12;
        zzan r13 = null;
    L16:
        zzm.zzc(r2);
        zzb(r13);
        return true;
    L12:
        IInterface r32 = r12.queryLocalInterface("com.google.android.apps.aicore.aidl.IAICoreService");
        if ((r32 instanceof zzan) == false) goto L15;
        r13 = (zzan) r32;
        goto L16
    L15:
        r13 = new zzal(r12);
        goto L16
    L5:
        if (r1 == 3) goto L8;
        return false;
    L8:
        int r14 = r2.readInt();
        String r33 = r2.readString();
        zzm.zzc(r2);
        zzc(r14, r33);
        return true;
    }
}
