package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zze implements View.OnClickListener {
    final /* synthetic */ UIMediaController zza;

    public zze(UIMediaController r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r1) {
        this.zza.onSkipPrevClicked(r1);
    }
}
