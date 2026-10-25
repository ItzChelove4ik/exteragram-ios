package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zacr extends com.google.android.gms.internal.base.zao {
    final /* synthetic */ zacs zaa;

    public zacr(zacs r1, Looper r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        super(r2);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message r4) {
        int r0 = r4.what;
        if (r0 != 0) goto L5;
        PendingResult r42 = (PendingResult) r4.obj;
        zacs r3 = this.zaa;
        Object r02 = r3.zaf();
        monitor-enter(r02);
        zacs r32 = (zacs) Preconditions.checkNotNull(r3.zae());     // Catch: Throwable -> L15
        if (r42 != null) goto L17;
        r32.zac(new Status(13, "Transform returned null"));     // Catch: Throwable -> L15
    L18:
        monitor-exit(r02);     // Catch: Throwable -> L15
        return;
    L17:
        r32.zaa(r42);     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        throw th;
    L5:
        if (r0 == 1) goto L8;
        StringBuilder r43 = new StringBuilder(String.valueOf(r0).length() + 59);
        r43.append("TransformationResultHandler received unknown message type: ");
        r43.append(r0);
        Log.e("TransformedResultImpl", r43.toString());
        return;
    L8:
        RuntimeException r33 = (RuntimeException) r4.obj;
        Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: ".concat(String.valueOf(r33.getMessage())));
        throw r33;
    }
}
