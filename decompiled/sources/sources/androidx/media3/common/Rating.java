package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public abstract class Rating {
    static final String FIELD_RATING_TYPE = null;

    static {
        FIELD_RATING_TYPE = Util.intToStringMaxRadix(0);
    }

    public Rating() {
    }

    public static Rating fromBundle(Bundle r2) {
        int r0 = r2.getInt(FIELD_RATING_TYPE, -1);
        if (r0 == 0) goto L19;
        if (r0 == 1) goto L17;
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L11;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown RatingType: ", r0);
        return null;
    L11:
        return ThumbRating.fromBundle(r2);
    L15:
        return StarRating.fromBundle(r2);
    L17:
        return PercentageRating.fromBundle(r2);
    L19:
        return HeartRating.fromBundle(r2);
    }

    public abstract boolean isRated();

    public abstract Bundle toBundle();
}
