package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzap extends zzl implements zzaq {
    public static zzaq zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.apps.aicore.aidl.IAiCoreServiceProvider");
        if ((r0 instanceof zzaq) == false) goto L10;
        return (zzaq) r0;
    L10:
        return new zzao(r2);
    }
}
