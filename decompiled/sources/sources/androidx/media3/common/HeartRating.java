package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class HeartRating extends Rating {
    private static final String FIELD_IS_HEART = null;
    private static final String FIELD_RATED = null;
    private final boolean isHeart;
    private final boolean rated;

    static {
        FIELD_RATED = Util.intToStringMaxRadix(1);
        FIELD_IS_HEART = Util.intToStringMaxRadix(2);
    }

    public HeartRating() {
        this.rated = false;
        this.isHeart = false;
    }

    public HeartRating(boolean r2) {
        this.rated = true;
        this.isHeart = r2;
    }

    public static HeartRating fromBundle(Bundle r3) {
        if (r3.getInt(Rating.FIELD_RATING_TYPE, -1) != 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r3.getBoolean(FIELD_RATED, false) == false) goto L11;
        return new HeartRating(r3.getBoolean(FIELD_IS_HEART, false));
    L11:
        return new HeartRating();
    L5:
        r0 = false;
        goto L6
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof HeartRating) == true) goto L5;
        return false;
    L5:
        HeartRating r42 = (HeartRating) r4;
        if (this.isHeart == r42.isHeart) goto L8;
    L11:
        return false;
    L8:
        if (this.rated != r42.rated) goto L11;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.rated), Boolean.valueOf(this.isHeart)});
    }

    public boolean isHeart() {
        return this.isHeart;
    }

    @Override // androidx.media3.common.Rating
    public boolean isRated() {
        return this.rated;
    }

    @Override // androidx.media3.common.Rating
    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(Rating.FIELD_RATING_TYPE, 0);
        r0.putBoolean(FIELD_RATED, this.rated);
        r0.putBoolean(FIELD_IS_HEART, this.isHeart);
        return r0;
    }
}
