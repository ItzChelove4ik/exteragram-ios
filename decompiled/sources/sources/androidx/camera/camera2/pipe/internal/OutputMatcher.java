package androidx.camera.camera2.pipe.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u001b\b\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\fR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/internal/OutputMatcher;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "initialOffset", "errorDelta", "<init>", "(JJ)V", "cameraOutputNumber", "outputNumber", _UrlKt.FRAGMENT_ENCODE_SET, "fuzzyEqual", "(JJ)Z", "J", "Lkotlinx/atomicfu/AtomicRef;", "currentOffset", "Lkotlinx/atomicfu/AtomicRef;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OutputMatcher {
    public static final Companion Companion = null;
    private static final OutputMatcher EXACT = null;
    private final AtomicRef<Long> currentOffset;
    private final long errorDelta;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/pipe/internal/OutputMatcher$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/internal/OutputMatcher;", "EXACT", "Landroidx/camera/camera2/pipe/internal/OutputMatcher;", "getEXACT", "()Landroidx/camera/camera2/pipe/internal/OutputMatcher;", _UrlKt.FRAGMENT_ENCODE_SET, "NS_PER_SECOND", "J", "ERROR_DETECTION_FPS", "DEFAULT_ERROR_NS", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final OutputMatcher getEXACT() {
            return OutputMatcher.access$getEXACT$cp();
        }
    }

    static {
        Companion = new Companion(null);
        EXACT = new OutputMatcher(0, 0);
    }

    public OutputMatcher(long r1, long r3) {
        this.errorDelta = r3;
        this.currentOffset = AtomicFU.atomic(Long.valueOf(r1));
    }

    public static final /* synthetic */ OutputMatcher access$getEXACT$cp() {
        return EXACT;
    }

    public final boolean fuzzyEqual(long r7, long r9) {
        long r0 = this.currentOffset.getValue().longValue();
        long r72 = (r7 - r9) + r0;
        if (r72 != 0) goto L5;
        return true;
    L5:
        long r4 = this.errorDelta;
        if (r4 != 0) goto L8;
        return false;
    L8:
        if (r72 < r4) goto L10;
        return false;
    L10:
        if (r72 <= (-r4)) goto L16;
        this.currentOffset.compareAndSet(Long.valueOf(r0), Long.valueOf(r0 - r72));
        return true;
    L16:
        return false;
    }
}
