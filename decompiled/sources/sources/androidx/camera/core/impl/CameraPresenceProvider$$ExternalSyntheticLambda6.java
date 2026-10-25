package androidx.camera.core.impl;

import androidx.camera.core.CameraPresenceListener;
import androidx.camera.core.impl.CameraPresenceProvider;
import kotlin.jvm.functions.Function1;

/* loaded from: classes3.dex */
public final /* synthetic */ class CameraPresenceProvider$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ CameraPresenceListener f$0;

    public /* synthetic */ CameraPresenceProvider$$ExternalSyntheticLambda6(CameraPresenceListener r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r1) {
        return Boolean.valueOf(CameraPresenceProvider.m596$r8$lambda$JU6f07PZ72IZthRnOxF4PINzoI(this.f$0, (CameraPresenceProvider.ListenerWrapper) r1));
    }
}
