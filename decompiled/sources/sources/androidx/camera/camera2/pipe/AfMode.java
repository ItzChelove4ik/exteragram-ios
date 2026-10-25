package androidx.camera.camera2.pipe;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\u000b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\bJ\u0010\u0010\u000f\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0005J\u001a\u0010\u0015\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/pipe/AfMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "isOn-impl", "(I)Z", "isOn", "isContinuous-impl", "isContinuous", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class AfMode {
    private static final int AUTO = 0;
    private static final int CONTINUOUS_PICTURE = 0;
    private static final int CONTINUOUS_VIDEO = 0;
    public static final Companion Companion = null;
    private static final int EDOF = 0;
    private static final int MACRO = 0;
    private static final int OFF = 0;
    private static final List<AfMode> values = null;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/pipe/AfMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "Landroidx/camera/camera2/pipe/AfMode;", "fromIntOrNull-MKXwA8g", "(I)Landroidx/camera/camera2/pipe/AfMode;", "fromIntOrNull", "AUTO", "I", "getAUTO-vHZNRtE", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "values", "Ljava/util/List;", "getValues", "()Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraControls.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/AfMode$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,225:1\n295#2,2:226\n*S KotlinDebug\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/AfMode$Companion\n*L\n52#1:226,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        /* renamed from: fromIntOrNull-MKXwA8g, reason: not valid java name */
        public final AfMode m128fromIntOrNullMKXwA8g(int r3) {
            Iterator<T> r2 = getValues().iterator();
        L4:
            if (r2.hasNext() == false) goto L8;
            Object r0 = r2.next();
            if (((AfMode) r0).m127unboximpl() != r3) goto L4;
        L10:
            return (AfMode) r0;
        L8:
            r0 = null;
            goto L10
        }

        /* renamed from: getAUTO-vHZNRtE, reason: not valid java name */
        public final int m129getAUTOvHZNRtE() {
            return AfMode.access$getAUTO$cp();
        }

        public final List<AfMode> getValues() {
            return AfMode.access$getValues$cp();
        }
    }

    static {
        Companion = new Companion(null);
        int r0 = m121constructorimpl(0);
        OFF = r0;
        int r1 = m121constructorimpl(1);
        AUTO = r1;
        int r2 = m121constructorimpl(2);
        MACRO = r2;
        int r3 = m121constructorimpl(3);
        CONTINUOUS_VIDEO = r3;
        int r4 = m121constructorimpl(4);
        CONTINUOUS_PICTURE = r4;
        int r5 = m121constructorimpl(5);
        EDOF = r5;
        values = CollectionsKt.listOf(new AfMode[]{m120boximpl(r0), m120boximpl(r1), m120boximpl(r2), m120boximpl(r3), m120boximpl(r4), m120boximpl(r5)});
    }

    private /* synthetic */ AfMode(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getAUTO$cp() {
        return AUTO;
    }

    public static final /* synthetic */ List access$getValues$cp() {
        return values;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AfMode m120boximpl(int r1) {
        return new AfMode(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m121constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m122equalsimpl(int r2, Object r3) {
        if ((r3 instanceof AfMode) == true) goto L6;
        return false;
    L6:
        if (r2 == ((AfMode) r3).m127unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m123hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: isContinuous-impl, reason: not valid java name */
    public static final boolean m124isContinuousimpl(int r1) {
        if (r1 != 3) goto L5;
        return true;
    L5:
        if (r1 == 4) goto L11;
        return false;
    L11:
        return true;
    }

    /* renamed from: isOn-impl, reason: not valid java name */
    public static final boolean m125isOnimpl(int r0) {
        if (r0 == 0) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m126toStringimpl(int r2) {
        return "AfMode(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m122equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m123hashCodeimpl(this.value);
    }

    public String toString() {
        return m126toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m127unboximpl() {
        return this.value;
    }
}
