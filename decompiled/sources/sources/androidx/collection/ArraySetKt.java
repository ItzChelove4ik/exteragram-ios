package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a'\u0010\u0004\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a1\u0010\b\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001f\u0010\n\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a'\u0010\u000e\u001a\u00020\r\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\f\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"E", "Landroidx/collection/ArraySet;", _UrlKt.FRAGMENT_ENCODE_SET, "hash", "binarySearchInternal", "(Landroidx/collection/ArraySet;I)I", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "indexOf", "(Landroidx/collection/ArraySet;Ljava/lang/Object;I)I", "indexOfNull", "(Landroidx/collection/ArraySet;)I", "size", _UrlKt.FRAGMENT_ENCODE_SET, "allocArrays", "(Landroidx/collection/ArraySet;I)V", "collection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ArraySetKt {
    public static final <E> void allocArrays(ArraySet<E> r1, int r2) {
        r1.setHashes$collection(new int[r2]);
        r1.setArray$collection(new Object[r2]);
    }

    public static final <E> int binarySearchInternal(ArraySet<E> r1, int r2) {
        return ContainerHelpersKt.binarySearch(r1.getHashes$collection(), r1.get_size$collection(), r2);
    L4:
        ArraySet$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public static final <E> int indexOf(ArraySet<E> r4, Object r5, int r6) {
        int r0 = r4.get_size$collection();
        if (r0 != 0) goto L6;
        return -1;
    L6:
        int r1 = binarySearchInternal(r4, r6);
        if (r1 >= 0) goto L10;
    L11:
        return r1;
    L10:
        if (Intrinsics.areEqual(r5, r4.getArray$collection()[r1]) == true) goto L11;
        int r2 = r1 + 1;
    L13:
        if (r2 >= r0) goto L20;
        if (r4.getHashes$collection()[r2] != r6) goto L20;
        if (Intrinsics.areEqual(r5, r4.getArray$collection()[r2]) == true) goto L18;
        r2 = r2 + 1;
        goto L13
    L18:
        return r2;
    L20:
        int r12 = r1 - 1;
    L21:
        if (r12 < 0) goto L29;
        if (r4.getHashes$collection()[r12] != r6) goto L29;
        if (Intrinsics.areEqual(r5, r4.getArray$collection()[r12]) == true) goto L26;
        r12 = r12 - 1;
        goto L21
    L26:
        return r12;
    L29:
        return ~r2;
    }

    public static final <E> int indexOfNull(ArraySet<E> r2) {
        return indexOf(r2, null, 0);
    }
}
