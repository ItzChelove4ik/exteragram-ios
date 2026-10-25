package androidx.camera.core.impl.utils;

import android.util.Size;
import java.util.Comparator;

/* loaded from: classes3.dex */
public final class CompareSizesByArea implements Comparator<Size> {
    private boolean mReverse;

    public CompareSizesByArea() {
        this(false);
    }

    public CompareSizesByArea(boolean r1) {
        this.mReverse = r1;
    }

    /* renamed from: compare, reason: avoid collision after fix types in other method */
    public int compare2(Size r5, Size r6) {
        int r52 = Long.signum((r5.getWidth() * r5.getHeight()) - (r6.getWidth() * r6.getHeight()));
        if (this.mReverse == true) goto L5;
        return r52;
    L5:
        return r52 * (-1);
    }

    @Override // java.util.Comparator
    public /* bridge */ /* synthetic */ int compare(Size r1, Size r2) {
        return compare2(r1, r2);
    }
}
