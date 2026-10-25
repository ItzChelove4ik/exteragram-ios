package com.exteragram.messenger.camera;

import android.util.Size;
import java.util.Set;
import java.util.function.ToIntFunction;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraXSession$$ExternalSyntheticLambda2 implements ToIntFunction {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ Set f$2;

    public /* synthetic */ CameraXSession$$ExternalSyntheticLambda2(int r1, boolean r2, Set r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r3) {
        return CameraXSession.$r8$lambda$5Bpb2uDaDq6YWT8gzHGYT5j5yQ0(this.f$0, this.f$1, this.f$2, (Size) r3);
    }
}
