package androidx.camera.camera2.impl;

import android.util.Size;
import androidx.camera.core.impl.SessionConfig;

/* loaded from: classes3.dex */
public final /* synthetic */ class MeteringRepeating$$ExternalSyntheticLambda0 implements SessionConfig.ErrorListener {
    public final /* synthetic */ MeteringRepeating f$0;
    public final /* synthetic */ Size f$1;

    public /* synthetic */ MeteringRepeating$$ExternalSyntheticLambda0(MeteringRepeating r1, Size r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.camera.core.impl.SessionConfig.ErrorListener
    public final void onError(SessionConfig r2, SessionConfig.SessionError r3) {
        MeteringRepeating.$r8$lambda$BPlB8isfuWKFvMqTcjDrqYh8Ecw(this.f$0, this.f$1, r2, r3);
    }
}
