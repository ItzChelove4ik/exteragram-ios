package com.google.android.gms.cloudmessaging;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledFuture;

/* loaded from: classes.dex */
final /* synthetic */ class zzab implements OnCompleteListener {
    private final /* synthetic */ Rpc zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ ScheduledFuture zzc;

    public /* synthetic */ zzab(Rpc r1, String r2, ScheduledFuture r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final /* synthetic */ void onComplete(Task r3) {
        this.zza.zzb(this.zzb, this.zzc, r3);
    }
}
