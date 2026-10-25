package androidx.camera.core.impl;

import androidx.camera.core.CameraState;
import androidx.lifecycle.Observer;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraPresenceProvider$$ExternalSyntheticLambda4 implements Observer {
    public final /* synthetic */ CameraPresenceProvider f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ CameraPresenceProvider$$ExternalSyntheticLambda4(CameraPresenceProvider r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object r2) {
        CameraPresenceProvider.$r8$lambda$NKb5tywml7fLh72E80xwb7S7jCQ(this.f$0, this.f$1, (CameraState) r2);
    }
}
