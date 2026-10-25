package com.google.android.gms.internal.measurement;

import android.app.ActivityManager;
import android.util.Log;
import com.google.common.base.Supplier;

/* loaded from: classes4.dex */
final /* synthetic */ class zzqh implements Supplier {
    static final /* synthetic */ zzqh zza = null;

    static {
        zza = new zzqh();
    }

    private /* synthetic */ zzqh() {
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        ActivityManager.RunningAppProcessInfo r0 = new ActivityManager.RunningAppProcessInfo();
        boolean r1 = false;
        ActivityManager.getMyMemoryState(r0);     // Catch: RuntimeException -> L7
        int r2 = r0.importance;
        StringBuilder r4 = new StringBuilder(String.valueOf(r2).length() + 17);
        r4.append("Memory state is: ");
        r4.append(r2);
        Log.i("PhenotypeProcessReaper", r4.toString());
        if (r0.importance < 400) goto L10;
        r1 = true;
    L10:
        return new Boolean(r1);
    L7:
        e = move-exception;
        Log.w("PhenotypeProcessReaper", "Failed to retrieve memory state, not killing process.", e);
        goto L10
    }
}
