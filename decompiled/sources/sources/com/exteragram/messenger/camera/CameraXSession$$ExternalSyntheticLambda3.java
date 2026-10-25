package com.exteragram.messenger.camera;

import android.util.Size;
import java.util.function.ToDoubleFunction;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraXSession$$ExternalSyntheticLambda3 implements ToDoubleFunction {
    public final /* synthetic */ double f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ CameraXSession$$ExternalSyntheticLambda3(double r1, int r3) {
        this.f$0 = r1;
        this.f$1 = r3;
    }

    @Override // java.util.function.ToDoubleFunction
    public final double applyAsDouble(Object r3) {
        return CameraXSession.$r8$lambda$wZRkxbdDRwhv33ZNzf22KoV2pfg(this.f$0, this.f$1, (Size) r3);
    }
}
