package androidx.camera.camera2.pipe;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u001a\u0010\u0013\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/AwbMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "isOn-impl", "(I)Z", "isOn", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class AwbMode {
    private static final int AUTO = 0;
    private static final int CLOUDY_DAYLIGHT = 0;
    public static final Companion Companion = null;
    private static final int DAYLIGHT = 0;
    private static final int FLUORESCENT = 0;
    private static final int INCANDESCENT = 0;
    private static final int OFF = 0;
    private static final int SHADE = 0;
    private static final int TWILIGHT = 0;
    private static final List<AwbMode> values = null;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/pipe/AwbMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "Landroidx/camera/camera2/pipe/AwbMode;", "fromIntOrNull--SaEiwI", "(I)Landroidx/camera/camera2/pipe/AwbMode;", "fromIntOrNull", _UrlKt.FRAGMENT_ENCODE_SET, "values", "Ljava/util/List;", "getValues", "()Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraControls.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/AwbMode$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,225:1\n295#2,2:226\n*S KotlinDebug\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/AwbMode$Companion\n*L\n129#1:226,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        /* renamed from: fromIntOrNull--SaEiwI, reason: not valid java name */
        public final AwbMode m147fromIntOrNullSaEiwI(int r3) {
            Iterator<T> r2 = getValues().iterator();
        L4:
            if (r2.hasNext() == false) goto L8;
            Object r0 = r2.next();
            if (((AwbMode) r0).m146unboximpl() != r3) goto L4;
        L10:
            return (AwbMode) r0;
        L8:
            r0 = null;
            goto L10
        }

        public final List<AwbMode> getValues() {
            return AwbMode.access$getValues$cp();
        }
    }

    static {
        Companion = new Companion(null);
        int r0 = m141constructorimpl(0);
        OFF = r0;
        int r1 = m141constructorimpl(1);
        AUTO = r1;
        int r2 = m141constructorimpl(6);
        CLOUDY_DAYLIGHT = r2;
        int r3 = m141constructorimpl(5);
        DAYLIGHT = r3;
        int r4 = m141constructorimpl(2);
        INCANDESCENT = r4;
        int r5 = m141constructorimpl(3);
        FLUORESCENT = r5;
        int r6 = m141constructorimpl(8);
        SHADE = r6;
        int r7 = m141constructorimpl(7);
        TWILIGHT = r7;
        values = CollectionsKt.listOf(new AwbMode[]{m140boximpl(r0), m140boximpl(r1), m140boximpl(r2), m140boximpl(r3), m140boximpl(r4), m140boximpl(r5), m140boximpl(r6), m140boximpl(r7)});
    }

    private /* synthetic */ AwbMode(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ List access$getValues$cp() {
        return values;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AwbMode m140boximpl(int r1) {
        return new AwbMode(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m141constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m142equalsimpl(int r2, Object r3) {
        if ((r3 instanceof AwbMode) == true) goto L6;
        return false;
    L6:
        if (r2 == ((AwbMode) r3).m146unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m143hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: isOn-impl, reason: not valid java name */
    public static final boolean m144isOnimpl(int r0) {
        if (r0 == 0) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m145toStringimpl(int r2) {
        return "AwbMode(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m142equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m143hashCodeimpl(this.value);
    }

    public String toString() {
        return m145toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m146unboximpl() {
        return this.value;
    }
}
