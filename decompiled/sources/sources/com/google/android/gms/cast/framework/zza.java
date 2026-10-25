package com.google.android.gms.cast.framework;

import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zza implements OnSuccessListener {
    private final /* synthetic */ MediaRouteButton zza;

    public /* synthetic */ zza(MediaRouteButton r1, MediaRouteDialogFactory r2) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r2) {
        MediaRouteButton r1 = this.zza;
        CastButtonFactory.zzc(r1, null, (Void) r2);
    }
}
