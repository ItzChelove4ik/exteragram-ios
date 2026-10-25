package androidx.camera.core;

import android.view.Surface;
import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class SurfaceRequest$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ Surface f$1;

    public /* synthetic */ SurfaceRequest$$ExternalSyntheticLambda6(Consumer r1, Surface r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SurfaceRequest.m588$r8$lambda$lW3msuxNLPi8yuGJSbbC4iUWKA(this.f$0, this.f$1);
    }
}
