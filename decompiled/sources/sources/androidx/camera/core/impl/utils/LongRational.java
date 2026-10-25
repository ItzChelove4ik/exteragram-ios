package androidx.camera.core.impl.utils;

import com.google.android.gms.cast.framework.media.NotificationOptions;

/* loaded from: classes3.dex */
final class LongRational {
    private final long mDenominator;
    private final long mNumerator;

    public LongRational(double r3) {
        this((long) (r3 * 10000.0d), NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    public LongRational(long r1, long r3) {
        this.mNumerator = r1;
        this.mDenominator = r3;
    }

    public long getDenominator() {
        return this.mDenominator;
    }

    public long getNumerator() {
        return this.mNumerator;
    }

    public String toString() {
        return this.mNumerator + "/" + this.mDenominator;
    }
}
