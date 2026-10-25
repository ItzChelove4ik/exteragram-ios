package androidx.camera.camera2.pipe;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u0005J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/FlashMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class FlashMode {
    public static final Companion Companion = null;
    private static final int OFF = 0;
    private static final int SINGLE = 0;
    private static final int TORCH = 0;
    private static final List<FlashMode> values = null;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/camera/camera2/pipe/FlashMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/FlashMode;", "OFF", "I", "getOFF-Le5xUZU", "()I", "TORCH", "getTORCH-Le5xUZU", _UrlKt.FRAGMENT_ENCODE_SET, "values", "Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraControls.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/FlashMode$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,225:1\n295#2,2:226\n*S KotlinDebug\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/FlashMode$Companion\n*L\n145#1:226,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getOFF-Le5xUZU, reason: not valid java name */
        public final int m261getOFFLe5xUZU() {
            return FlashMode.access$getOFF$cp();
        }

        /* renamed from: getTORCH-Le5xUZU, reason: not valid java name */
        public final int m262getTORCHLe5xUZU() {
            return FlashMode.access$getTORCH$cp();
        }
    }

    static {
        Companion = new Companion(null);
        int r0 = m256constructorimpl(0);
        OFF = r0;
        int r1 = m256constructorimpl(1);
        SINGLE = r1;
        int r2 = m256constructorimpl(2);
        TORCH = r2;
        values = CollectionsKt.listOf(new FlashMode[]{m255boximpl(r0), m255boximpl(r1), m255boximpl(r2)});
    }

    private /* synthetic */ FlashMode(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getOFF$cp() {
        return OFF;
    }

    public static final /* synthetic */ int access$getTORCH$cp() {
        return TORCH;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FlashMode m255boximpl(int r1) {
        return new FlashMode(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m256constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m257equalsimpl(int r2, Object r3) {
        if ((r3 instanceof FlashMode) == true) goto L6;
        return false;
    L6:
        if (r2 == ((FlashMode) r3).m260unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m258hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m259toStringimpl(int r2) {
        return "FlashMode(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m257equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m258hashCodeimpl(this.value);
    }

    public String toString() {
        return m259toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m260unboximpl() {
        return this.value;
    }
}
