package com.google.android.gms.cast.framework.media.widget;

import android.view.View;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzh implements View.OnClickListener {
    final /* synthetic */ ExpandedControllerActivity zza;

    public zzh(ExpandedControllerActivity r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r1) {
        ExpandedControllerActivity r0 = this.zza;
        if (r0.zzi().isClickable() == false) goto L8;
        RemoteMediaClient r02 = r0.zza();
        if (r02 == null) goto L9;
        r02.skipAd();
        return;
    L9:
        return;
    }
}
