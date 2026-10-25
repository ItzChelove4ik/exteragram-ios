package com.google.android.gms.cloudmessaging;

import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;

/* loaded from: classes4.dex */
final /* synthetic */ class zzaa implements Runnable {
    private final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzaa(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        IOException r0 = new IOException("TIMEOUT");
        if (this.zza.trySetException(r0) == false) goto L6;
        Log.w("Rpc", "No response");
        return;
    }
}
