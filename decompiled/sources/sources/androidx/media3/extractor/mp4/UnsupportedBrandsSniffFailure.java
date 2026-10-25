package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;
import com.google.common.primitives.ImmutableIntArray;

/* loaded from: classes4.dex */
public final class UnsupportedBrandsSniffFailure implements SniffFailure {
    public final ImmutableIntArray compatibleBrands;
    public final int majorBrand;

    public UnsupportedBrandsSniffFailure(int r1, int[] r2) {
        this.majorBrand = r1;
        if (r2 == null) goto L5;
        ImmutableIntArray r12 = ImmutableIntArray.copyOf(r2);
    L6:
        this.compatibleBrands = r12;
        return;
    L5:
        r12 = ImmutableIntArray.of();
        goto L6
    }
}
