package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public final class zae extends zai {
    protected final BaseImplementation$ApiMethodImpl zaa;

    public zae(int r1, BaseImplementation$ApiMethodImpl r2) {
        super(r1);
        this.zaa = (BaseImplementation$ApiMethodImpl) Preconditions.checkNotNull(r2, "Null methods are not runnable.");
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zad(Status r2) {
        this.zaa.setFailedResult(r2);     // Catch: IllegalStateException -> L4
        return;
    L4:
        e = move-exception;
        Log.w("ApiCallRunner", "Exception reporting failure", e);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zae(Exception r6) {
        String r1 = r6.getClass().getSimpleName();
        String r62 = r6.getLocalizedMessage();
        int r2 = r1.length();
        int r22 = r2 + 2;
        StringBuilder r4 = new StringBuilder(r22 + String.valueOf(r62).length());
        r4.append(r1);
        r4.append(": ");
        r4.append(r62);
        Status r0 = new Status(10, r4.toString());
        this.zaa.setFailedResult(r0);     // Catch: IllegalStateException -> L5
        return;
    L5:
        e = move-exception;
        Log.w("ApiCallRunner", "Exception reporting failure", e);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zaf(zaaa r1, boolean r2) {
        r1.zaa(this.zaa, r2);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zag(zabk r2) {
        this.zaa.run(r2.zaf());     // Catch: RuntimeException -> L4
        return;
    L4:
        e = move-exception;
        zae(e);
    }
}
