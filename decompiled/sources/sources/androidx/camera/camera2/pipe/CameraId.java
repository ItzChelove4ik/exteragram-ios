package androidx.camera.camera2.pipe;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@kotlin.Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\b\u0087@\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0088\u0001\u0003\u0092\u0001\u00020\u0002¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/pipe/CameraId;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "value", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "toString-impl", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(Ljava/lang/String;)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "equals", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
@SourceDebugExtension({"SMAP\nCameraDevices.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraDevices.kt\nandroidx/camera/camera2/pipe/CameraId\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,228:1\n1#2:229\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraId {
    public static final Companion Companion = null;
    private final String value;

    @kotlin.Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/CameraId$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    private /* synthetic */ CameraId(String r1) {
        this.value = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ CameraId m227boximpl(String r1) {
        return new CameraId(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static String m228constructorimpl(String r1) {
        if (StringsKt.isBlank(r1) == true) goto L5;
        return r1;
    L5:
        f$$ExternalSyntheticBUOutline1.m("CameraId cannot be null or blank!");
        return null;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m229equalsimpl(String r2, Object r3) {
        if ((r3 instanceof CameraId) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2, ((CameraId) r3).m233unboximpl()) == true) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m230equalsimpl0(String r0, String r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m231hashCodeimpl(String r0) {
        return r0.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m232toStringimpl(String r2) {
        return "CameraId-" + r2;
    }

    public boolean equals(Object r1) {
        return m229equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m231hashCodeimpl(this.value);
    }

    public String toString() {
        return m232toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ String m233unboximpl() {
        return this.value;
    }
}
