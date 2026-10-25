package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes4.dex */
public final class zzao extends zzk implements zzaq {
    public zzao(IBinder r2) {
        super(r2, "com.google.android.apps.aicore.aidl.IAiCoreServiceProvider");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzaq
    public final void zzd(zzas r3) {
        Parcel r0 = zza();
        int r1 = zzm.$r8$clinit;
        r0.writeStrongBinder(r3);
        zzc(2, r0);
    }
}
