package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a)\u0010\u0004\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0007\u001a\u00020\u0006\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0002¢\u0006\u0004\b\u0007\u0010\b\"\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"E", "Landroidx/collection/SparseArrayCompat;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "commonGet", "(Landroidx/collection/SparseArrayCompat;I)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "gc", "(Landroidx/collection/SparseArrayCompat;)V", _UrlKt.FRAGMENT_ENCODE_SET, "DELETED", "Ljava/lang/Object;", "collection"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSparseArrayCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseArrayCompat.kt\nandroidx/collection/SparseArrayCompatKt\n+ 2 CollectionPlatformUtils.jvm.kt\nandroidx/collection/CollectionPlatformUtils\n*L\n1#1,498:1\n217#1,6:499\n217#1,6:505\n327#1,30:511\n327#1,30:541\n422#1,9:572\n24#2:571\n*S KotlinDebug\n*F\n+ 1 SparseArrayCompat.kt\nandroidx/collection/SparseArrayCompatKt\n*L\n229#1:499,6\n235#1:505,6\n361#1:511,30\n369#1:541,30\n440#1:572,9\n399#1:571\n*E\n"})
/* loaded from: classes3.dex */
public abstract class SparseArrayCompatKt {
    private static final Object DELETED = null;

    static {
        DELETED = new Object();
    }

    public static final /* synthetic */ void access$gc(SparseArrayCompat r0) {
        gc(r0);
    }

    public static final /* synthetic */ Object access$getDELETED$p() {
        return DELETED;
    }

    public static final <E> E commonGet(SparseArrayCompat<E> r2, int r3) {
        int r32 = ContainerHelpersKt.binarySearch(r2.keys, r2.size, r3);
        if (r32 < 0) goto L8;
        E r22 = (E) r2.values[r32];
        if (r22 == DELETED) goto L10;
        return r22;
    L10:
        return null;
    L8:
        return null;
    }

    private static final <E> void gc(SparseArrayCompat<E> r8) {
        int r0 = r8.size;
        int[] r1 = r8.keys;
        Object[] r2 = r8.values;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r0) goto L10;
        Object r6 = r2[r4];
        if (r6 == DELETED) goto L9;
        if (r4 == r5) goto L8;
        r1[r5] = r1[r4];
        r2[r5] = r6;
        r2[r4] = null;
    L8:
        r5 = r5 + 1;
    L9:
        r4 = r4 + 1;
        goto L3
    L10:
        r8.garbage = false;
        r8.size = r5;
    }
}
