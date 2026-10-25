package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzbo extends zzl implements zzbp {
    public zzbo() {
        super("com.google.android.apps.aicore.aidl.ITokenizationCallback");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzl
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L5;
        int r12 = r2.readInt();
        zzm.zzc(r2);
        zzb(r12);
        return true;
    L5:
        if (r1 != 3) goto L7;
        int r13 = r2.readInt();
        zzm.zzc(r2);
        zzc(r13);
        return true;
    L7:
        if (r1 != 4) goto L9;
        zzct r14 = (zzct) zzm.zza(r2, zzct.CREATOR);
        zzm.zzc(r2);
        zzd(r14);
        return true;
    L9:
        if (r1 == 5) goto L12;
        return false;
    L12:
        zzq r15 = (zzq) zzm.zza(r2, zzq.CREATOR);
        zzm.zzc(r2);
        zze(r15);
        return true;
    }
}
