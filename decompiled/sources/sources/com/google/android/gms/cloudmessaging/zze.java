package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
final /* synthetic */ class zze implements Runnable {
    private final /* synthetic */ Context zza;
    private final /* synthetic */ CloudMessage zzb;
    private final /* synthetic */ CountDownLatch zzc;

    public /* synthetic */ zze(Context r1, CloudMessage r2, CountDownLatch r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        CloudMessage r0 = this.zzb;
        if (TextUtils.isEmpty(r0.getMessageId()) == false) goto L5;
        Task r02 = Tasks.forResult(null);
    L9:
        CountDownLatch r4 = this.zzc;
        r02.addOnCompleteListener(zzh.zza, new zzf(r4));
        return;
    L5:
        Bundle r1 = new Bundle();
        r1.putString("google.message_id", r0.getMessageId());
        Integer r03 = r0.zza();
        if (r03 == null) goto L8;
        r1.putInt("google.product_id", r03.intValue());
    L8:
        Context r04 = this.zza;
        r1.putBoolean("supports_message_handled", true);
        r02 = zzv.zza(r04).zzb(2, r1);
        goto L9
    }
}
