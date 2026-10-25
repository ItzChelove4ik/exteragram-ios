package androidx.camera.core.impl;

import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class LensFacingCameraFilter implements CameraFilter {
    private final int mLensFacing;

    public LensFacingCameraFilter(int r1) {
        this.mLensFacing = r1;
    }

    @Override // androidx.camera.core.CameraFilter
    public List<CameraInfo> filter(List<CameraInfo> r5) {
        ArrayList r0 = new ArrayList();
        Iterator<CameraInfo> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L8;
        CameraInfo r1 = r52.next();
        Preconditions.checkArgument(r1 instanceof CameraInfoInternal, "The camera info doesn't contain internal implementation.");
        if (r1.getLensFacing() != this.mLensFacing) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    public int getLensFacing() {
        return this.mLensFacing;
    }
}
