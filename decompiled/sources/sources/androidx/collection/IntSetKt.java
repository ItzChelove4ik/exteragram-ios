package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/collection/MutableIntSet;", "EmptyIntSet", "Landroidx/collection/MutableIntSet;", _UrlKt.FRAGMENT_ENCODE_SET, "EmptyIntArray", "[I", "getEmptyIntArray", "()[I", "collection"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIntSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntSet.kt\nandroidx/collection/IntSetKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,884:1\n1#2:885\n*E\n"})
/* loaded from: classes3.dex */
public abstract class IntSetKt {
    private static final int[] EmptyIntArray = null;
    private static final MutableIntSet EmptyIntSet = null;

    static {
        EmptyIntSet = new MutableIntSet(0);
        EmptyIntArray = new int[0];
    }

    public static final int[] getEmptyIntArray() {
        return EmptyIntArray;
    }
}
