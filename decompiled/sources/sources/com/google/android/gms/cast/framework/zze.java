package com.google.android.gms.cast.framework;

import android.view.MenuItem;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes4.dex */
final /* synthetic */ class zze implements OnSuccessListener {
    public /* synthetic */ zze(MediaRouteDialogFactory r1) {
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Object r1) {
        CastButtonFactory.zzb(null, (MenuItem) r1);
    }
}
