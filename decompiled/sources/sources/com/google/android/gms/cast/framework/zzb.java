package com.google.android.gms.cast.framework;

import android.view.MenuItem;
import androidx.mediarouter.app.MediaRouteActionProvider;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzb implements OnSuccessListener {
    private final /* synthetic */ MediaRouteActionProvider zza;
    private final /* synthetic */ TaskCompletionSource zzb;
    private final /* synthetic */ MenuItem zzc;

    public /* synthetic */ zzb(MediaRouteActionProvider r1, MediaRouteDialogFactory r2, TaskCompletionSource r3, MenuItem r4) {
        this.zza = r1;
        this.zzb = r3;
        this.zzc = r4;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r4) {
        MediaRouteActionProvider r3 = this.zza;
        CastButtonFactory.zzd(r3, null, this.zzb, this.zzc, (CastContext) r4);
    }
}
