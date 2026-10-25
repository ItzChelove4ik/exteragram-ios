package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0003\u001a\u0017\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0003\u001a\u0017\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0007\u0010\u0003\"\u0014\u0010\t\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n\"\"\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\r0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f*\f\b\u0000\u0010\u0011\"\u00020\u00102\u00020\u0010*\f\b\u0000\u0010\u0012\"\u00020\u00102\u00020\u0010*\f\b\u0000\u0010\u0013\"\u00020\u00102\u00020\u0010¨\u0006\u0014"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "capacity", "nextCapacity", "(I)I", "n", "normalizeCapacity", "loadedCapacity", "unloadedCapacity", _UrlKt.FRAGMENT_ENCODE_SET, "EmptyGroup", "[J", "Landroidx/collection/MutableScatterMap;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "EmptyScatterMap", "Landroidx/collection/MutableScatterMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Bitmask", "Group", "StaticBitmask", "collection"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nScatterMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1787:1\n1254#1,6:1789\n1220#1:1795\n1220#1:1796\n1220#1:1797\n1297#1:1798\n1#2:1788\n*S KotlinDebug\n*F\n+ 1 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n*L\n1230#1:1789,6\n1262#1:1795\n1265#1:1796\n1268#1:1797\n1303#1:1798\n*E\n"})
/* loaded from: classes3.dex */
public abstract class ScatterMapKt {

    @JvmField
    public static final long[] EmptyGroup = null;
    private static final MutableScatterMap EmptyScatterMap = null;

    static {
        EmptyGroup = new long[]{-9187201950435737345L, -1};
        EmptyScatterMap = new MutableScatterMap(0);
    }

    public static final int loadedCapacity(int r1) {
        if (r1 != 7) goto L7;
        return 6;
    L7:
        return r1 - (r1 / 8);
    }

    public static final int nextCapacity(int r0) {
        if (r0 != 0) goto L6;
        return 6;
    L6:
        return (r0 * 2) + 1;
    }

    public static final int normalizeCapacity(int r1) {
        if (r1 > 0) goto L4;
        return 0;
    L4:
        return (-1) >>> Integer.numberOfLeadingZeros(r1);
    }

    public static final int unloadedCapacity(int r2) {
        if (r2 != 7) goto L7;
        return 8;
    L7:
        return r2 + ((r2 - 1) / 7);
    }
}
