package androidx.camera.core.impl;

import androidx.camera.core.impl.CameraPresenceProvider;
import java.util.Set;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraPresenceProvider$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CameraPresenceProvider.ListenerWrapper f$0;
    public final /* synthetic */ Set f$1;

    public /* synthetic */ CameraPresenceProvider$$ExternalSyntheticLambda2(CameraPresenceProvider.ListenerWrapper r1, Set r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CameraPresenceProvider.$r8$lambda$nayhqDvZFdl3bDjg8uQf2cjlMg8(this.f$0, this.f$1);
    }
}
