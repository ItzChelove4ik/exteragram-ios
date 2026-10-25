package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class StarRating extends Rating {
    private static final String FIELD_MAX_STARS = null;
    private static final String FIELD_STAR_RATING = null;
    private final int maxStars;
    private final float starRating;

    static {
        FIELD_MAX_STARS = Util.intToStringMaxRadix(1);
        FIELD_STAR_RATING = Util.intToStringMaxRadix(2);
    }

    public StarRating(int r3) {
        if (r3 <= 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0, "maxStars must be a positive integer");
        this.maxStars = r3;
        this.starRating = -1.0f;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public StarRating(int r5, float r6) {
        boolean r0 = false;
        if (r5 <= 0) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkArgument(r2, "maxStars must be a positive integer");
        if (r6 >= 0.0f) goto L9;
    L11:
        Assertions.checkArgument(r0, "starRating is out of range [0, maxStars]");
        this.maxStars = r5;
        this.starRating = r6;
        return;
    L9:
        if (r6 > r5) goto L11;
        r0 = true;
        goto L11
    L5:
        r2 = false;
        goto L6
    }

    public static StarRating fromBundle(Bundle r3) {
        if (r3.getInt(Rating.FIELD_RATING_TYPE, -1) != 2) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        int r02 = r3.getInt(FIELD_MAX_STARS, 5);
        float r32 = r3.getFloat(FIELD_STAR_RATING, -1.0f);
        if (r32 != (-1.0f)) goto L11;
        return new StarRating(r02);
    L11:
        return new StarRating(r02, r32);
    L5:
        r0 = false;
        goto L6
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof StarRating) == true) goto L5;
        return false;
    L5:
        StarRating r42 = (StarRating) r4;
        if (this.maxStars == r42.maxStars) goto L8;
    L11:
        return false;
    L8:
        if (this.starRating != r42.starRating) goto L11;
        return true;
    }

    public int getMaxStars() {
        return this.maxStars;
    }

    public float getStarRating() {
        return this.starRating;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.maxStars), Float.valueOf(this.starRating)});
    }

    @Override // androidx.media3.common.Rating
    public boolean isRated() {
        if (this.starRating == (-1.0f)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.common.Rating
    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(Rating.FIELD_RATING_TYPE, 2);
        r0.putInt(FIELD_MAX_STARS, this.maxStars);
        r0.putFloat(FIELD_STAR_RATING, this.starRating);
        return r0;
    }
}
