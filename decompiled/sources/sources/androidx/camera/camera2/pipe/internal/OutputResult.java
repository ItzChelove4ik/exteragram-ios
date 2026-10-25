package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.OutputStatus;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0081@\u0018\u0000 \u0019*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002:\u0001\u0019B\u0013\b\u0002\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\r\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\u0012\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0000X\u0080\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0018\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015\u0088\u0001\u0003\u0092\u0001\u0004\u0018\u00010\u0002¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/pipe/internal/OutputResult;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "result", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(Ljava/lang/Object;)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "equals", "Ljava/lang/Object;", "getAvailable-impl", "(Ljava/lang/Object;)Z", "available", "getFailure-impl", "failure", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@JvmInline
/* loaded from: classes3.dex */
public final class OutputResult<T> {
    public static final Companion Companion = null;
    private final Object result;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/internal/OutputResult$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nOutputResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n+ 2 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult\n*L\n1#1,110:1\n64#1:111\n68#1:112\n55#2,5:113\n44#2,4:118\n*S KotlinDebug\n*F\n+ 1 OutputResult.kt\nandroidx/camera/camera2/pipe/internal/OutputResult$Companion\n*L\n72#1:111\n79#1:112\n97#1:113,5\n104#1:118,4\n*E\n"})
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

    private /* synthetic */ OutputResult(Object r1) {
        this.result = r1;
    }

    /* renamed from: access$constructor-impl, reason: not valid java name */
    public static final /* synthetic */ Object m571access$constructorimpl(Object r0) {
        return m573constructorimpl(r0);
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ OutputResult m572boximpl(Object r1) {
        return new OutputResult(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    private static <T> Object m573constructorimpl(Object r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m574equalsimpl(Object r2, Object r3) {
        if ((r3 instanceof OutputResult) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2, ((OutputResult) r3).m579unboximpl()) == true) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: getAvailable-impl, reason: not valid java name */
    public static final boolean m575getAvailableimpl(Object r1) {
        if (m576getFailureimpl(r1) == true) goto L7;
        if (r1 == null) goto L9;
        return true;
    L9:
        return false;
    L7:
        return false;
    }

    /* renamed from: getFailure-impl, reason: not valid java name */
    public static final boolean m576getFailureimpl(Object r0) {
        return r0 instanceof OutputStatus;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m577hashCodeimpl(Object r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return r0.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m578toStringimpl(Object r2) {
        return "OutputResult(result=" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m574equalsimpl(this.result, r1);
    }

    public int hashCode() {
        return m577hashCodeimpl(this.result);
    }

    public String toString() {
        return m578toStringimpl(this.result);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m579unboximpl() {
        return this.result;
    }
}
