package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Parcel;

/* loaded from: classes4.dex */
public abstract class zzbd extends zzl implements zzbe {
    public zzbd() {
        super("com.google.android.apps.aicore.aidl.IDownloadListener2");
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzl
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 2) goto L5;
        String r12 = r2.readString();
        long r32 = r2.readLong();
        zzm.zzc(r2);
        zzb(r12, r32);
        return true;
    L5:
        if (r1 != 3) goto L7;
        String r13 = r2.readString();
        long r33 = r2.readLong();
        zzm.zzc(r2);
        zzc(r13, r33);
        return true;
    L7:
        if (r1 != 4) goto L9;
        String r14 = r2.readString();
        int r34 = r2.readInt();
        String r42 = r2.readString();
        zzm.zzc(r2);
        zzd(r14, r34, r42);
        return true;
    L9:
        if (r1 == 5) goto L12;
        return false;
    L12:
        String r15 = r2.readString();
        zzm.zzc(r2);
        zze(r15);
        return true;
    }
}
