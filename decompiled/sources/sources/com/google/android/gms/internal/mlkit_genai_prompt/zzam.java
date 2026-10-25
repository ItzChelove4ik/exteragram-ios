package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public abstract class zzam extends zzl implements zzan {
    public static zzan zzb(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.apps.aicore.aidl.IAICoreService");
        if ((r0 instanceof zzan) == false) goto L10;
        return (zzan) r0;
    L10:
        return new zzal(r2);
    }
}
