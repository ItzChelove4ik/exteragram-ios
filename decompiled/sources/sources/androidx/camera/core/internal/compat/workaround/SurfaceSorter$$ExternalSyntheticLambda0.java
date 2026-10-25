package androidx.camera.core.internal.compat.workaround;

import androidx.camera.core.impl.SessionConfig;
import java.util.Comparator;

/* loaded from: classes3.dex */
public final /* synthetic */ class SurfaceSorter$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ SurfaceSorter f$0;

    public /* synthetic */ SurfaceSorter$$ExternalSyntheticLambda0(SurfaceSorter r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return SurfaceSorter.$r8$lambda$UabBosrqJDhzqUepvZGts2v0jo8(this.f$0, (SessionConfig.OutputConfig) r1, (SessionConfig.OutputConfig) r2);
    }
}
