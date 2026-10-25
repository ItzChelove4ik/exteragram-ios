package com.google.android.gms.cast.framework;

import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzd implements OnSuccessListener {
    private final /* synthetic */ MediaRouteButton zza;
    private final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzd(MediaRouteButton r1, MediaRouteDialogFactory r2, TaskCompletionSource r3) {
        this.zza = r1;
        this.zzb = r3;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r3) {
        MediaRouteButton r2 = this.zza;
        CastButtonFactory.zze(r2, null, this.zzb, (CastContext) r3);
    }
}
