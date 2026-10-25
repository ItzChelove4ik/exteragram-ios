package com.google.android.gms.cast.framework;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
final /* synthetic */ class zzu implements OnCompleteListener {
    private final /* synthetic */ zzv zza;

    public /* synthetic */ zzu(zzv r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final /* synthetic */ void onComplete(Task r2) {
        this.zza.zza.zze("joinApplication", r2);
    }
}
