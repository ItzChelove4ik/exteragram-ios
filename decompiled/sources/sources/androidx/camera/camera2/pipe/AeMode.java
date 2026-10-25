package androidx.camera.camera2.pipe;

import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u001a\u0010\u0013\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/pipe/AeMode;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "isOn-impl", "(I)Z", "isOn", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(I)Ljava/lang/String;", "toString", "hashCode-impl", "hashCode", "other", "equals-impl", "(ILjava/lang/Object;)Z", "equals", "I", "getValue", "()I", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class AeMode {
    public static final Companion Companion = null;
    private static final int OFF = 0;
    private static final int ON = 0;
    private static final int ON_ALWAYS_FLASH = 0;
    private static final int ON_AUTO_FLASH = 0;
    private static final int ON_AUTO_FLASH_REDEYE = 0;
    private static final int ON_EXTERNAL_FLASH = 0;
    private static final int ON_LOW_LIGHT_BOOST_BRIGHTNESS_PRIORITY = 0;
    private static final List<AeMode> values = null;
    private final int value;

    @kotlin.Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/AeMode$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "value", "Landroidx/camera/camera2/pipe/AeMode;", "fromIntOrNull-kQd0u18", "(I)Landroidx/camera/camera2/pipe/AeMode;", "fromIntOrNull", "OFF", "I", "getOFF-bOjpiJc", "()I", "ON", "getON-bOjpiJc", _UrlKt.FRAGMENT_ENCODE_SET, "values", "Ljava/util/List;", "getValues", "()Ljava/util/List;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraControls.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/AeMode$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,225:1\n295#2,2:226\n*S KotlinDebug\n*F\n+ 1 CameraControls.kt\nandroidx/camera/camera2/pipe/AeMode$Companion\n*L\n88#1:226,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        /* renamed from: fromIntOrNull-kQd0u18, reason: not valid java name */
        public final AeMode m117fromIntOrNullkQd0u18(int r3) {
            Iterator<T> r2 = getValues().iterator();
        L4:
            if (r2.hasNext() == false) goto L8;
            Object r0 = r2.next();
            if (((AeMode) r0).m116unboximpl() != r3) goto L4;
        L10:
            return (AeMode) r0;
        L8:
            r0 = null;
            goto L10
        }

        /* renamed from: getOFF-bOjpiJc, reason: not valid java name */
        public final int m118getOFFbOjpiJc() {
            return AeMode.access$getOFF$cp();
        }

        /* renamed from: getON-bOjpiJc, reason: not valid java name */
        public final int m119getONbOjpiJc() {
            return AeMode.access$getON$cp();
        }

        public final List<AeMode> getValues() {
            return AeMode.access$getValues$cp();
        }
    }

    static {
        Companion = new Companion(null);
        int r0 = m110constructorimpl(0);
        OFF = r0;
        int r1 = m110constructorimpl(1);
        ON = r1;
        int r2 = m110constructorimpl(3);
        ON_ALWAYS_FLASH = r2;
        int r3 = m110constructorimpl(2);
        ON_AUTO_FLASH = r3;
        int r4 = m110constructorimpl(4);
        ON_AUTO_FLASH_REDEYE = r4;
        int r5 = m110constructorimpl(5);
        ON_EXTERNAL_FLASH = r5;
        int r6 = m110constructorimpl(6);
        ON_LOW_LIGHT_BOOST_BRIGHTNESS_PRIORITY = r6;
        values = CollectionsKt.listOf(new AeMode[]{m109boximpl(r0), m109boximpl(r1), m109boximpl(r3), m109boximpl(r2), m109boximpl(r4), m109boximpl(r5), m109boximpl(r6)});
    }

    private /* synthetic */ AeMode(int r1) {
        this.value = r1;
    }

    public static final /* synthetic */ int access$getOFF$cp() {
        return OFF;
    }

    public static final /* synthetic */ int access$getON$cp() {
        return ON;
    }

    public static final /* synthetic */ List access$getValues$cp() {
        return values;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ AeMode m109boximpl(int r1) {
        return new AeMode(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m110constructorimpl(int r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m111equalsimpl(int r2, Object r3) {
        if ((r3 instanceof AeMode) == true) goto L6;
        return false;
    L6:
        if (r2 == ((AeMode) r3).m116unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m112equalsimpl0(int r0, int r1) {
        if (r0 != r1) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m113hashCodeimpl(int r0) {
        return Integer.hashCode(r0);
    }

    /* renamed from: isOn-impl, reason: not valid java name */
    public static final boolean m114isOnimpl(int r0) {
        if (r0 == 0) goto L5;
        return true;
    L5:
        return false;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m115toStringimpl(int r2) {
        return "AeMode(value=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m111equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m113hashCodeimpl(this.value);
    }

    public String toString() {
        return m115toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m116unboximpl() {
        return this.value;
    }
}
