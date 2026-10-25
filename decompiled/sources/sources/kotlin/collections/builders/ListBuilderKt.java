package kotlin.collections.builders;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0080\u0080\u0004¢\u0006\u0002\u0010\u0005\u001a?\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\b*\n\u0012\u0006\b\u0001\u0012\u0002H\b0\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\b0\fH\u0082\u0080\u0004¢\u0006\u0002\u0010\r\u001a/\u0010\u000e\u001a\u00020\u0004\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0082\u0080\u0004¢\u0006\u0002\u0010\u000f\u001a;\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00012\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0014\u001a-\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00012\u0006\u0010\u0016\u001a\u00020\u0004H\u0080\u0080\u0004¢\u0006\u0002\u0010\u0017\u001a'\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0004H\u0080\u0080\u0004¢\u0006\u0002\u0010\u001b\u001a/\u0010\u001c\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0080\u0080\u0004¢\u0006\u0002\u0010\u001f¨\u0006 "}, d2 = {"arrayOfUninitializedElements", _UrlKt.FRAGMENT_ENCODE_SET, "E", "size", _UrlKt.FRAGMENT_ENCODE_SET, "(I)[Ljava/lang/Object;", "subarrayContentToString", _UrlKt.FRAGMENT_ENCODE_SET, "T", "offset", "length", "thisCollection", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;IILjava/util/Collection;)Ljava/lang/String;", "subarrayContentHashCode", "([Ljava/lang/Object;II)I", "subarrayContentEquals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;IILjava/util/List;)Z", "copyOfUninitializedElements", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "resetAt", _UrlKt.FRAGMENT_ENCODE_SET, "index", "([Ljava/lang/Object;I)V", "resetRange", "fromIndex", "toIndex", "([Ljava/lang/Object;II)V", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilderKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,724:1\n1#2:725\n*E\n"})
/* loaded from: classes.dex */
public final class ListBuilderKt {
    public static final /* synthetic */ boolean access$subarrayContentEquals(Object[] r0, int r1, int r2, List r3) {
        return subarrayContentEquals(r0, r1, r2, r3);
    }

    public static final /* synthetic */ int access$subarrayContentHashCode(Object[] r0, int r1, int r2) {
        return subarrayContentHashCode(r0, r1, r2);
    }

    public static final /* synthetic */ String access$subarrayContentToString(Object[] r0, int r1, int r2, Collection r3) {
        return subarrayContentToString(r0, r1, r2, r3);
    }

    public static final <E> E[] arrayOfUninitializedElements(int r0) {
        if (r0 >= 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("capacity must be non-negative.");
        return null;
    L4:
        return (E[]) new Object[r0];
    }

    public static final <T> T[] copyOfUninitializedElements(T[] r0, int r1) {
        return (T[]) Arrays.copyOf(r0, r1);
    }

    public static final <E> void resetAt(E[] r1, int r2) {
        r1[r2] = null;
    }

    public static final <E> void resetRange(E[] r0, int r1, int r2) {
    L2:
        if (r1 >= r2) goto L4;
        resetAt(r0, r1);
        r1 = r1 + 1;
        goto L2
    }

    private static final <T> boolean subarrayContentEquals(T[] r4, int r5, int r6, List<?> r7) {
        if (r6 == r7.size()) goto L5;
        return false;
    L5:
        int r0 = 0;
    L6:
        if (r0 >= r6) goto L11;
        if (Intrinsics.areEqual(r4[r5 + r0], r7.get(r0)) == false) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    private static final <T> int subarrayContentHashCode(T[] r4, int r5, int r6) {
        int r0 = 1;
        int r2 = 0;
    L3:
        if (r2 >= r6) goto L9;
        T r3 = r4[r5 + r2];
        int r02 = r0 * 31;
        if (r3 == null) goto L7;
        int r32 = r3.hashCode();
    L8:
        r0 = r02 + r32;
        r2 = r2 + 1;
        goto L3
    L7:
        r32 = 0;
        goto L8
    L9:
        return r0;
    }

    private static final <T> String subarrayContentToString(T[] r3, int r4, int r5, Collection<? extends T> r6) {
        StringBuilder r0 = new StringBuilder((r5 * 3) + 2);
        r0.append("[");
        int r1 = 0;
    L3:
        if (r1 >= r5) goto L11;
        if (r1 <= 0) goto L6;
        r0.append(", ");
    L6:
        T r2 = r3[r4 + r1];
        if (r2 != r6) goto L9;
        r0.append("(this Collection)");
    L10:
        r1 = r1 + 1;
        goto L3
    L9:
        r0.append(r2);
        goto L10
    L11:
        r0.append("]");
        return r0.toString();
    }
}
