package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ThumbRating extends Rating {
    private static final String FIELD_IS_THUMBS_UP = null;
    private static final String FIELD_RATED = null;
    private final boolean isThumbsUp;
    private final boolean rated;

    static {
        FIELD_RATED = Util.intToStringMaxRadix(1);
        FIELD_IS_THUMBS_UP = Util.intToStringMaxRadix(2);
    }

    public ThumbRating() {
        this.rated = false;
        this.isThumbsUp = false;
    }

    public ThumbRating(boolean r2) {
        this.rated = true;
        this.isThumbsUp = r2;
    }

    public static ThumbRating fromBundle(Bundle r3) {
        if (r3.getInt(Rating.FIELD_RATING_TYPE, -1) != 3) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        if (r3.getBoolean(FIELD_RATED, false) == false) goto L11;
        return new ThumbRating(r3.getBoolean(FIELD_IS_THUMBS_UP, false));
    L11:
        return new ThumbRating();
    L5:
        r0 = false;
        goto L6
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof ThumbRating) == true) goto L5;
        return false;
    L5:
        ThumbRating r42 = (ThumbRating) r4;
        if (this.isThumbsUp == r42.isThumbsUp) goto L8;
    L11:
        return false;
    L8:
        if (this.rated != r42.rated) goto L11;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Boolean.valueOf(this.rated), Boolean.valueOf(this.isThumbsUp)});
    }

    @Override // androidx.media3.common.Rating
    public boolean isRated() {
        return this.rated;
    }

    public boolean isThumbsUp() {
        return this.isThumbsUp;
    }

    @Override // androidx.media3.common.Rating
    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(Rating.FIELD_RATING_TYPE, 3);
        r0.putBoolean(FIELD_RATED, this.rated);
        r0.putBoolean(FIELD_IS_THUMBS_UP, this.isThumbsUp);
        return r0;
    }
}
