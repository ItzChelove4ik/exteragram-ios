package androidx.camera.core.impl.utils;

import androidx.camera.core.resolutionselector.ResolutionSelector;

/* loaded from: classes3.dex */
public abstract class ResolutionSelectorUtil {
    public static ResolutionSelector overrideResolutionSelectors(ResolutionSelector r1, ResolutionSelector r2) {
        if (r2 != null) goto L4;
        return r1;
    L4:
        if (r1 != null) goto L6;
        return r2;
    L6:
        ResolutionSelector.Builder r12 = ResolutionSelector.Builder.fromResolutionSelector(r1);
        if (r2.getAspectRatioStrategy() == null) goto L10;
        r12.setAspectRatioStrategy(r2.getAspectRatioStrategy());
    L10:
        if (r2.getResolutionStrategy() == null) goto L13;
        r12.setResolutionStrategy(r2.getResolutionStrategy());
    L13:
        if (r2.getResolutionFilter() == null) goto L16;
        r12.setResolutionFilter(r2.getResolutionFilter());
    L16:
        if (r2.getAllowedResolutionMode() == 0) goto L19;
        r12.setAllowedResolutionMode(r2.getAllowedResolutionMode());
    L19:
        return r12.build();
    }
}
