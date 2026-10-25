package androidx.collection;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u0005¨\u0006\u000e"}, d2 = {"Landroidx/collection/MutableFloatList;", "Landroidx/collection/FloatList;", _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "element", _UrlKt.FRAGMENT_ENCODE_SET, "add", "(F)Z", "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "ensureCapacity", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFloatList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatList.kt\nandroidx/collection/MutableFloatList\n+ 2 FloatList.kt\nandroidx/collection/FloatList\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,976:1\n559#1:978\n365#2:977\n70#2:979\n237#2,6:982\n70#2:988\n70#2:989\n70#2:996\n13344#3,2:980\n1687#3,6:990\n*S KotlinDebug\n*F\n+ 1 FloatList.kt\nandroidx/collection/MutableFloatList\n*L\n695#1:978\n631#1:977\n755#1:979\n768#1:982,6\n782#1:988\n828#1:989\n845#1:996\n763#1:980,2\n830#1:990,6\n*E\n"})
/* loaded from: classes3.dex */
public final class MutableFloatList extends FloatList {
    public MutableFloatList(int r2) {
        super(r2, null);
    }

    public final boolean add(float r4) {
        ensureCapacity(this._size + 1);
        float[] r0 = this.content;
        int r2 = this._size;
        r0[r2] = r4;
        this._size = r2 + 1;
        return true;
    }

    public final void ensureCapacity(int r3) {
        float[] r0 = this.content;
        if (r0.length >= r3) goto L6;
        this.content = Arrays.copyOf(r0, Math.max(r3, (r0.length * 3) / 2));
        return;
    }
}
