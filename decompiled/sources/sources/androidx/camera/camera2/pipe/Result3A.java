package androidx.camera.camera2.pipe;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0017B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\rR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/Result3A;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Result3A$Status;", "status", "Landroidx/camera/camera2/pipe/FrameMetadata;", "frameMetadata", "<init>", "(ILandroidx/camera/camera2/pipe/FrameMetadata;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getStatus-JvTi9ms", "Landroidx/camera/camera2/pipe/FrameMetadata;", "getFrameMetadata", "()Landroidx/camera/camera2/pipe/FrameMetadata;", "Status", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Result3A {
    private final FrameMetadata frameMetadata;
    private final int status;

    @kotlin.Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0087@\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/pipe/Result3A$Status;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @JvmInline
    public static final class Status {
        public static final Companion Companion = null;
        private static final int FRAME_LIMIT_REACHED = 0;
        private static final int OK = 0;
        private static final int SUBMIT_CANCELLED = 0;
        private static final int SUBMIT_FAILED = 0;
        private static final int TIME_LIMIT_REACHED = 0;

        @kotlin.Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007¨\u0006\u0011"}, d2 = {"Landroidx/camera/camera2/pipe/Result3A$Status$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "OK", "Landroidx/camera/camera2/pipe/Result3A$Status;", "getOK-JvTi9ms", "()I", "I", "FRAME_LIMIT_REACHED", "getFRAME_LIMIT_REACHED-JvTi9ms", "TIME_LIMIT_REACHED", "getTIME_LIMIT_REACHED-JvTi9ms", "SUBMIT_CANCELLED", "getSUBMIT_CANCELLED-JvTi9ms", "SUBMIT_FAILED", "getSUBMIT_FAILED-JvTi9ms", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            /* renamed from: getFRAME_LIMIT_REACHED-JvTi9ms, reason: not valid java name */
            public final int m383getFRAME_LIMIT_REACHEDJvTi9ms() {
                return Status.access$getFRAME_LIMIT_REACHED$cp();
            }

            /* renamed from: getOK-JvTi9ms, reason: not valid java name */
            public final int m384getOKJvTi9ms() {
                return Status.access$getOK$cp();
            }

            /* renamed from: getSUBMIT_CANCELLED-JvTi9ms, reason: not valid java name */
            public final int m385getSUBMIT_CANCELLEDJvTi9ms() {
                return Status.access$getSUBMIT_CANCELLED$cp();
            }

            /* renamed from: getSUBMIT_FAILED-JvTi9ms, reason: not valid java name */
            public final int m386getSUBMIT_FAILEDJvTi9ms() {
                return Status.access$getSUBMIT_FAILED$cp();
            }

            /* renamed from: getTIME_LIMIT_REACHED-JvTi9ms, reason: not valid java name */
            public final int m387getTIME_LIMIT_REACHEDJvTi9ms() {
                return Status.access$getTIME_LIMIT_REACHED$cp();
            }
        }

        static {
            Companion = new Companion(null);
            OK = m379constructorimpl(0);
            FRAME_LIMIT_REACHED = m379constructorimpl(1);
            TIME_LIMIT_REACHED = m379constructorimpl(2);
            SUBMIT_CANCELLED = m379constructorimpl(3);
            SUBMIT_FAILED = m379constructorimpl(4);
        }

        public static final /* synthetic */ int access$getFRAME_LIMIT_REACHED$cp() {
            return FRAME_LIMIT_REACHED;
        }

        public static final /* synthetic */ int access$getOK$cp() {
            return OK;
        }

        public static final /* synthetic */ int access$getSUBMIT_CANCELLED$cp() {
            return SUBMIT_CANCELLED;
        }

        public static final /* synthetic */ int access$getSUBMIT_FAILED$cp() {
            return SUBMIT_FAILED;
        }

        public static final /* synthetic */ int access$getTIME_LIMIT_REACHED$cp() {
            return TIME_LIMIT_REACHED;
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        private static int m379constructorimpl(int r0) {
            return r0;
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m380equalsimpl0(int r0, int r1) {
            if (r0 != r1) goto L5;
            return true;
        L5:
            return false;
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m381hashCodeimpl(int r0) {
            return Integer.hashCode(r0);
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m382toStringimpl(int r2) {
            return "Status(value=" + r2 + ')';
        }
    }

    private Result3A(int r1, FrameMetadata r2) {
        this.status = r1;
        this.frameMetadata = r2;
    }

    public /* synthetic */ Result3A(int r1, FrameMetadata r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2, null);
    }

    public /* synthetic */ Result3A(int r1, FrameMetadata r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Result3A) == true) goto L8;
        return false;
    L8:
        Result3A r52 = (Result3A) r5;
        if (Status.m380equalsimpl0(this.status, r52.status) == true) goto L12;
        return false;
    L12:
        if (Intrinsics.areEqual(this.frameMetadata, r52.frameMetadata) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final FrameMetadata getFrameMetadata() {
        return this.frameMetadata;
    }

    /* renamed from: getStatus-JvTi9ms, reason: not valid java name */
    public final int m378getStatusJvTi9ms() {
        return this.status;
    }

    public int hashCode() {
        int r0 = Status.m381hashCodeimpl(this.status) * 31;
        FrameMetadata r1 = this.frameMetadata;
        if (r1 != null) goto L5;
        int r12 = 0;
    L7:
        return r0 + r12;
    L5:
        r12 = r1.hashCode();
        goto L7
    }

    public String toString() {
        return "Result3A(status=" + Status.m382toStringimpl(this.status) + ", frameMetadata=" + this.frameMetadata + ')';
    }
}
