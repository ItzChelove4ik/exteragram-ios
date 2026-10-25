package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH\u0087\u0080\u0004¢\u0006\u0004\b\t\u0010\n\u001a7\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0087\u0080\u0004¢\u0006\u0004\b\t\u0010\f\u001a\u007f\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000f2\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001a$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\u0088\u0004¢\u0006\u0002\u0010\u0014\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u0003\"\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"EMPTY", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/Object;", "MAX_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "collectionToArray", "collection", _UrlKt.FRAGMENT_ENCODE_SET, "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "CollectionToArray")
@SourceDebugExtension({"SMAP\nCollectionToArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionToArray.kt\nkotlin/jvm/internal/CollectionToArray\n*L\n1#1,88:1\n63#1,22:89\n63#1,22:111\n*S KotlinDebug\n*F\n+ 1 CollectionToArray.kt\nkotlin/jvm/internal/CollectionToArray\n*L\n22#1:89,22\n37#1:111,22\n*E\n"})
/* loaded from: classes.dex */
public final class CollectionToArray {
    private static final Object[] EMPTY = null;
    private static final int MAX_SIZE = 2147483645;

    static {
        EMPTY = new Object[0];
    }

    @Deprecated(message = "This function will be made internal in a future release")
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.9")
    @JvmName(name = "toArray")
    public static final Object[] toArray(Collection<?> r4) {
        int r0 = r4.size();
        if (r0 == 0) goto L5;
        Iterator<?> r42 = r4.iterator();
        if (r42.hasNext() == false) goto L9;
        Object[] r02 = new Object[r0];
        int r1 = 0;
    L11:
        int r2 = r1 + 1;
        r02[r1] = r42.next();
        if (r2 < r02.length) goto L26;
        if (r42.hasNext() == false) goto L15;
        int r12 = ((r2 * 3) + 1) >>> 1;
        if (r12 > r2) goto L23;
        r12 = MAX_SIZE;
        if (r2 < MAX_SIZE) goto L23;
        throw new OutOfMemoryError();
    L23:
        r02 = Arrays.copyOf(r02, r12);
    L24:
        r1 = r2;
        goto L11
    L15:
        return r02;
    L26:
        if (r42.hasNext() == true) goto L24;
        return Arrays.copyOf(r02, r2);
    L9:
        return EMPTY;
    L5:
        return EMPTY;
    }

    @Deprecated(message = "This function will be made internal in a future release")
    @DeprecatedSinceKotlin(errorSince = "2.1", warningSince = "1.9")
    @JvmName(name = "toArray")
    public static final Object[] toArray(Collection<?> r5, Object[] r6) {
        r6.getClass();
        int r0 = r5.size();
        int r2 = 0;
        if (r0 == 0) goto L5;
        Iterator<?> r52 = r5.iterator();
        if (r52.hasNext() == true) goto L15;
        if (r6.length <= 0) goto L13;
        r6[0] = null;
    L13:
        return r6;
    L15:
        if (r0 > r6.length) goto L17;
        Object[] r02 = r6;
    L18:
        int r3 = r2 + 1;
        r02[r2] = r52.next();
        if (r3 < r02.length) goto L33;
        if (r52.hasNext() == false) goto L22;
        int r22 = ((r3 * 3) + 1) >>> 1;
        if (r22 > r3) goto L30;
        r22 = MAX_SIZE;
        if (r3 < MAX_SIZE) goto L30;
        throw new OutOfMemoryError();
    L30:
        r02 = Arrays.copyOf(r02, r22);
    L31:
        r2 = r3;
        goto L18
    L22:
        return r02;
    L33:
        if (r52.hasNext() == true) goto L31;
        if (r02 != r6) goto L38;
        r6[r3] = null;
        return r6;
    L38:
        return Arrays.copyOf(r02, r3);
    L17:
        r02 = (Object[]) Array.newInstance(r6.getClass().getComponentType(), r0);
        goto L18
    L5:
        if (r6.length <= 0) goto L13;
        r6[0] = null;
        return r6;
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8 */
    private static final Object[] toArrayImpl(Collection<?> r2, Function0<Object[]> r3, Function1<? super Integer, Object[]> r4, Function2<? super Object[], ? super Integer, Object[]> r5) {
        int r0 = r2.size();
        if (r0 == 0) goto L5;
        Iterator<?> r22 = r2.iterator();
        if (r22.hasNext() == false) goto L9;
        Object[] r32 = r4.invoke(Integer.valueOf(r0));
        int r42 = 0;
        ?? r33 = r32;
    L11:
        int r02 = r42 + 1;
        r33[r42] = r22.next();
        if (r02 < r33.length) goto L26;
        if (r22.hasNext() == false) goto L15;
        int r43 = ((r02 * 3) + 1) >>> 1;
        if (r43 > r02) goto L23;
        r43 = MAX_SIZE;
        if (r02 < MAX_SIZE) goto L23;
        throw new OutOfMemoryError();
    L23:
        r33 = Arrays.copyOf(r33, r43);
    L24:
        r42 = r02;
        r33 = r33;
        goto L11
    L15:
        return r33;
    L26:
        if (r22.hasNext() == true) goto L24;
        return r5.invoke(r33, Integer.valueOf(r02));
    L9:
        return r3.invoke();
    L5:
        return r3.invoke();
    }
}
