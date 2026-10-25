package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzbg extends zzl implements zzbh {
    public zzbg() {
        super("com.google.android.apps.aicore.aidl.ILLMResultWithInfoCallback");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzl
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L5;
        zzci r12 = (zzci) zzm.zza(r2, zzci.CREATOR);
        zzm.zzc(r2);
        zzb(r12);
        return true;
    L5:
        if (r1 == 3) goto L8;
        return false;
    L8:
        zzaj r13 = (zzaj) zzm.zza(r2, zzaj.CREATOR);
        zzm.zzc(r2);
        zzc(r13);
        return true;
    }
}
