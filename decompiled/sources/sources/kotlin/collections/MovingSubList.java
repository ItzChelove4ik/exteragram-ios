package kotlin.collections;

import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u0017\bF\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\nH\u0086\u0080\u0004J\u0017\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\nH\u0096\u0082\u0004¢\u0006\u0002\u0010\u0011R\u0015\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0012\u001a\u00020\n8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lkotlin/collections/MovingSubList;", "E", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "list", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/List;)V", "fromIndex", _UrlKt.FRAGMENT_ENCODE_SET, "_size", "move", _UrlKt.FRAGMENT_ENCODE_SET, "toIndex", "get", "index", "(I)Ljava/lang/Object;", "size", "getSize", "()I", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class MovingSubList<E> extends AbstractList<E> implements RandomAccess {
    private int _size;
    private int fromIndex;
    private final List<E> list;

    /* JADX WARN: Multi-variable type inference failed */
    public MovingSubList(List<? extends E> r1) {
        this.list = r1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public E get(int r3) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this._size);
        return this.list.get(this.fromIndex + r3);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this._size;
    }

    public final void move(int r3, int r4) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r3, r4, this.list.size());
        this.fromIndex = r3;
        this._size = r4 - r3;
    }
}
