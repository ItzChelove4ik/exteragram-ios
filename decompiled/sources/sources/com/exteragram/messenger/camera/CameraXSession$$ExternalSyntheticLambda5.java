package com.exteragram.messenger.camera;

import android.util.Size;
import androidx.camera.core.resolutionselector.ResolutionFilter;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraXSession$$ExternalSyntheticLambda5 implements ResolutionFilter {
    public final /* synthetic */ Size f$0;
    public final /* synthetic */ Set f$1;

    public /* synthetic */ CameraXSession$$ExternalSyntheticLambda5(Size r1, Set r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.camera.core.resolutionselector.ResolutionFilter
    public final List filter(List r2, int r3) {
        return CameraXSession.$r8$lambda$UgBo7gMDjWn9HWQDXGU1spN6jFQ(this.f$0, this.f$1, r2, r3);
    }
}
