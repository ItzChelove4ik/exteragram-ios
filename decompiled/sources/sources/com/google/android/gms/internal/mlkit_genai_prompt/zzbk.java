package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzbk extends zzl implements zzbl {
    public zzbk() {
        super("com.google.android.apps.aicore.aidl.ILLMStreamingCallback");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzl
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L6;
        int r12 = r2.readInt();
        String r32 = r2.readString();
        zzm.zzc(r2);
        zzd(r12, r32);
        return true;
    L6:
        return false;
    }
}
