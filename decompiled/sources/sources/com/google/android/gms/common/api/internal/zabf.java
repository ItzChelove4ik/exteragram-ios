package com.google.android.gms.common.api.internal;

import android.os.Message;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zabf implements BackgroundDetector.BackgroundStateChangeListener {
    final /* synthetic */ GoogleApiManager zaa;

    public zabf(GoogleApiManager r1) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean r3) {
        Boolean r32 = Boolean.valueOf(r3);
        GoogleApiManager r2 = this.zaa;
        Message r33 = r2.zaF().obtainMessage(1, r32);
        r2.zaF().sendMessage(r33);
    }
}
