package androidx.camera.camera2.pipe.core;

import android.os.SystemClock;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0007\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\n\u0010\t¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/pipe/core/SystemClockOffsets;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "realtimeNsToUtcMs", "realtimeNsToMonotonicNs", "<init>", "(JJ)V", "J", "getRealtimeNsToUtcMs", "()J", "getRealtimeNsToMonotonicNs", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SystemClockOffsets {
    public static final Companion Companion = null;
    private final long realtimeNsToMonotonicNs;
    private final long realtimeNsToUtcMs;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u0007\u0010\u0006J\u000f\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/core/SystemClockOffsets$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "estimateRealtimeNsToUtcMs", "()J", "estimateRealtimeNsToMonotonicNs", "Landroidx/camera/camera2/pipe/core/SystemClockOffsets;", "estimate", "()Landroidx/camera/camera2/pipe/core/SystemClockOffsets;", "NS_PER_MS", "J", "NS_PER_MS_X_2", _UrlKt.FRAGMENT_ENCODE_SET, "MEASUREMENT_ITERATIONS", "I", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        private final long estimateRealtimeNsToMonotonicNs() {
            long r0 = LongCompanionObject.MAX_VALUE;
            long r2 = 0;
            int r13 = 0;
        L4:
            if (r13 >= 3) goto L9;
            long r4 = System.nanoTime();
            long r6 = SystemClock.elapsedRealtimeNanos();
            long r8 = System.nanoTime();
            long r10 = r8 - r4;
            if (r10 >= r0) goto L8;
            r2 = r6 - ((r4 + r8) / 2);
            r0 = r10;
        L8:
            r13 = r13 + 1;
            goto L4
        L9:
            return r2;
        }

        @JvmStatic
        private final long estimateRealtimeNsToUtcMs() {
            long r0 = LongCompanionObject.MAX_VALUE;
            long r2 = 0;
            int r13 = 0;
        L4:
            if (r13 >= 3) goto L9;
            long r4 = SystemClock.elapsedRealtimeNanos();
            long r6 = System.currentTimeMillis();
            long r8 = SystemClock.elapsedRealtimeNanos();
            long r10 = r8 - r4;
            if (r10 >= r0) goto L8;
            r2 = ((r4 + r8) / 2000000) - r6;
            r0 = r10;
        L8:
            r13 = r13 + 1;
            goto L4
        L9:
            return r2;
        }

        @JvmStatic
        public final SystemClockOffsets estimate() {
            return new SystemClockOffsets(estimateRealtimeNsToUtcMs(), estimateRealtimeNsToMonotonicNs(), null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private SystemClockOffsets(long r1, long r3) {
        this.realtimeNsToUtcMs = r1;
        this.realtimeNsToMonotonicNs = r3;
    }

    public /* synthetic */ SystemClockOffsets(long r1, long r3, DefaultConstructorMarker r5) {
        this(r1, r3);
    }

    public final long getRealtimeNsToMonotonicNs() {
        return this.realtimeNsToMonotonicNs;
    }
}
