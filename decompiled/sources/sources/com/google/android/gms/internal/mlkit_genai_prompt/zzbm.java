package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzbm extends zzl implements zzbn {
    public zzbm() {
        super("com.google.android.apps.aicore.aidl.IPrepareInferenceEngineCallback");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzl
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L5;
        zzb();
        return true;
    L5:
        if (r1 != 3) goto L7;
        int r12 = r2.readInt();
        zzm.zzc(r2);
        zzc(r12);
        return true;
    L7:
        if (r1 == 4) goto L10;
        return false;
    L10:
        zzq r13 = (zzq) zzm.zza(r2, zzq.CREATOR);
        zzm.zzc(r2);
        zzd(r13);
        return true;
    }
}
