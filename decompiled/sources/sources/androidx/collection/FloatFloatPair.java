package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087@\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0000\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u000f\u0010\r\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0011\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0016\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0017\u0088\u0001\b\u0092\u0001\u00020\u0007¨\u0006\u0018"}, d2 = {"Landroidx/collection/FloatFloatPair;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "first", "second", "constructor-impl", "(FF)J", _UrlKt.FRAGMENT_ENCODE_SET, "packedValue", "(J)J", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(J)Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode-impl", "(J)I", "hashCode", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals-impl", "(JLjava/lang/Object;)Z", "equals", "J", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@JvmInline
@SourceDebugExtension({"SMAP\nFloatFloatPair.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatFloatPair.kt\nandroidx/collection/FloatFloatPair\n+ 2 PackingUtils.kt\nandroidx/collection/PackingUtilsKt\n+ 3 PackingHelpers.jvm.kt\nandroidx/collection/internal/PackingHelpers_jvmKt\n*L\n1#1,84:1\n51#1:92\n55#1:94\n22#2,3:85\n22#3:88\n22#3:89\n22#3:90\n22#3:91\n22#3:93\n*S KotlinDebug\n*F\n+ 1 FloatFloatPair.kt\nandroidx/collection/FloatFloatPair\n*L\n82#1:92\n82#1:94\n47#1:85,3\n51#1:88\n55#1:89\n67#1:90\n80#1:91\n82#1:93\n*E\n"})
/* loaded from: classes3.dex */
public final class FloatFloatPair {

    @JvmField
    public final long packedValue;

    private /* synthetic */ FloatFloatPair(long r1) {
        this.packedValue = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FloatFloatPair m658boximpl(long r1) {
        return new FloatFloatPair(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m659constructorimpl(float r4, float r5) {
        long r0 = Float.floatToRawIntBits(r4);
        return m660constructorimpl((Float.floatToRawIntBits(r5) & 4294967295L) | (r0 << 32));
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m660constructorimpl(long r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m661equalsimpl(long r4, Object r6) {
        if ((r6 instanceof FloatFloatPair) == true) goto L6;
        return false;
    L6:
        if (r4 == ((FloatFloatPair) r6).m664unboximpl()) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m662hashCodeimpl(long r0) {
        return Long.hashCode(r0);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m663toStringimpl(long r3) {
        return "(" + Float.intBitsToFloat((int) (r3 >> 32)) + ", " + Float.intBitsToFloat((int) (r3 & 4294967295L)) + ')';
    }

    public boolean equals(Object r3) {
        return m661equalsimpl(this.packedValue, r3);
    }

    public int hashCode() {
        return m662hashCodeimpl(this.packedValue);
    }

    public String toString() {
        return m663toStringimpl(this.packedValue);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m664unboximpl() {
        return this.packedValue;
    }
}
