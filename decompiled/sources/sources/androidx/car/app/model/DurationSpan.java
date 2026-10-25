package androidx.car.app.model;

import android.annotation.SuppressLint;
import j$.time.Duration;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class DurationSpan extends CarSpan {
    private final long mDurationSeconds;

    public static final class Api26Impl {
        public static DurationSpan create(Duration r3) {
            Objects.requireNonNull(r3);
            return new DurationSpan(r3.getSeconds());
        }
    }

    private DurationSpan() {
        this.mDurationSeconds = 0;
    }

    public DurationSpan(long r1) {
        this.mDurationSeconds = r1;
    }

    public static DurationSpan create(long r1) {
        return new DurationSpan(r1);
    }

    public static DurationSpan create(Duration r0) {
        return Api26Impl.create(r0);
    }

    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if ((r6 instanceof DurationSpan) == true) goto L9;
        return false;
    L9:
        if (this.mDurationSeconds != ((DurationSpan) r6).mDurationSeconds) goto L11;
        return true;
    L11:
        return false;
    }

    @SuppressLint({"MethodNameUnits"})
    public long getDurationSeconds() {
        return this.mDurationSeconds;
    }

    public int hashCode() {
        long r0 = this.mDurationSeconds;
        return (int) (r0 ^ (r0 >>> 32));
    }

    public String toString() {
        return "[seconds: " + this.mDurationSeconds + "]";
    }
}
