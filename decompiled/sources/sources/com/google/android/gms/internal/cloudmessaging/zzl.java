package com.google.android.gms.internal.cloudmessaging;

import android.content.pm.PackageManager;
import com.google.android.gms.cloudmessaging.RegisterRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzl implements RemoteCall {
    private final /* synthetic */ zzm zza;
    private final /* synthetic */ RegisterRequest zzb;

    public /* synthetic */ zzl(zzm r1, RegisterRequest r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final /* synthetic */ void accept(Object r4, Object r5) {
        zzm r0 = this.zza;
        zzd r42 = (zzd) r4;
        zzi r1 = new zzi(r0, (TaskCompletionSource) r5);
        int r52 = 0;
        r52 = Wrappers.packageManager(r0.getApplicationContext()).getPackageInfo(r0.getApplicationContext().getPackageName(), 0).versionCode;     // Catch: PackageManager.NameNotFoundException -> L6
    L4:
        RegisterRequest r3 = this.zzb;
        r3.zza(r52);
        ((zze) r42.getService()).zzc(r1, r3, zzq.zza(r42.getContext()).toBuilder().setCallbackSupportEnabled(true).build());
    }
}
