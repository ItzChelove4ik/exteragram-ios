package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0005\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u001a\u0010\u0004\u001a\u00020\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/collection/MutableFloatSet;", "EmptyFloatSet", "Landroidx/collection/MutableFloatSet;", _UrlKt.FRAGMENT_ENCODE_SET, "EmptyFloatArray", "[F", "getEmptyFloatArray", "()[F", "collection"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFloatSet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatSet.kt\nandroidx/collection/FloatSetKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,886:1\n1#2:887\n*E\n"})
/* loaded from: classes3.dex */
public abstract class FloatSetKt {
    private static final float[] EmptyFloatArray = null;
    private static final MutableFloatSet EmptyFloatSet = null;

    static {
        EmptyFloatSet = new MutableFloatSet(0);
        EmptyFloatArray = new float[0];
    }

    public static final float[] getEmptyFloatArray() {
        return EmptyFloatArray;
    }
}
