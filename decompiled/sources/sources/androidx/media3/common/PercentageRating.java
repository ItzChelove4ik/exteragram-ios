package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class PercentageRating extends Rating {
    private static final String FIELD_PERCENT = null;
    private final float percent;

    static {
        FIELD_PERCENT = Util.intToStringMaxRadix(1);
    }

    public PercentageRating() {
        this.percent = -1.0f;
    }

    public PercentageRating(float r3) {
        if (r3 >= 0.0f) goto L5;
    L7:
        boolean r0 = false;
    L8:
        Assertions.checkArgument(r0, "percent must be in the range of [0, 100]");
        this.percent = r3;
        return;
    L5:
        if (r3 > 100.0f) goto L7;
        r0 = true;
        goto L8
    }

    public static PercentageRating fromBundle(Bundle r2) {
        boolean r1 = true;
        if (r2.getInt(Rating.FIELD_RATING_TYPE, -1) == 1) goto L6;
        r1 = false;
    L6:
        Assertions.checkArgument(r1);
        float r22 = r2.getFloat(FIELD_PERCENT, -1.0f);
        if (r22 != (-1.0f)) goto L11;
        return new PercentageRating();
    L11:
        return new PercentageRating(r22);
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof PercentageRating) == true) goto L6;
        return false;
    L6:
        if (this.percent != ((PercentageRating) r3).percent) goto L9;
        return true;
    L9:
        return false;
    }

    public float getPercent() {
        return this.percent;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Float.valueOf(this.percent)});
    }

    @Override // androidx.media3.common.Rating
    public boolean isRated() {
        if (this.percent == (-1.0f)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.common.Rating
    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(Rating.FIELD_RATING_TYPE, 1);
        r0.putFloat(FIELD_PERCENT, this.percent);
        return r0;
    }
}
