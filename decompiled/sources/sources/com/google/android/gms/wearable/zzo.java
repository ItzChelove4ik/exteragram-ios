package com.google.android.gms.wearable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.internal.zzev;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzo implements OnCompleteListener {
    public final /* synthetic */ zzaa zza;
    public final /* synthetic */ zzev zzb;

    public /* synthetic */ zzo(zzaa r1, zzev r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r1) {
        zzaa.zzm(this.zzb, r1);
    }
}
