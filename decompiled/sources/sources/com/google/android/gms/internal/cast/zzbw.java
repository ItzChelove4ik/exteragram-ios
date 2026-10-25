package com.google.android.gms.internal.cast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
final /* synthetic */ class zzbw implements OnCompleteListener {
    private final /* synthetic */ zzbx zza;

    public /* synthetic */ zzbw(zzbx r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final /* synthetic */ void onComplete(Task r1) {
        this.zza.zzw(r1);
    }
}
