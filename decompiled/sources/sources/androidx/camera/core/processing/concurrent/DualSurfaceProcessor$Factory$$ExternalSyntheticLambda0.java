package androidx.camera.core.processing.concurrent;

import androidx.camera.core.CompositionSettings;
import androidx.camera.core.DynamicRange;
import kotlin.jvm.functions.Function3;

/* loaded from: classes3.dex */
public final /* synthetic */ class DualSurfaceProcessor$Factory$$ExternalSyntheticLambda0 implements Function3 {
    public /* synthetic */ DualSurfaceProcessor$Factory$$ExternalSyntheticLambda0() {
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object r1, Object r2, Object r3) {
        return new DualSurfaceProcessor((DynamicRange) r1, (CompositionSettings) r2, (CompositionSettings) r3);
    }
}
