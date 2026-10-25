package com.google.android.gms.cloudmessaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
final /* synthetic */ class zzf implements OnCompleteListener {
    private final /* synthetic */ CountDownLatch zza;

    public /* synthetic */ zzf(CountDownLatch r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final /* synthetic */ void onComplete(Task r1) {
        this.zza.countDown();
    }
}
