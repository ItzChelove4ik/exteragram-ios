package com.google.android.gms.internal.mlkit_genai_prompt;

import android.app.ActivityManager;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final /* synthetic */ class zzec implements Callable {
    private final /* synthetic */ int zza;

    public /* synthetic */ zzec(int r1) {
        this.zza = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        ActivityManager.RunningAppProcessInfo r0 = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(r0);
        if (r0.importance > this.zza) goto L5;
        boolean r1 = true;
    L7:
        return Boolean.valueOf(r1);
    L5:
        r1 = false;
        goto L7
    }
}
