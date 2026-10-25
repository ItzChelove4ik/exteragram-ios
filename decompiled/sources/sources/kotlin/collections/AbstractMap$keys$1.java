package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: Add missing generic type declarations: [K] */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0017\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0096\u0082\u0004R\u0015\u0010\b\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"kotlin/collections/AbstractMap$keys$1", "Lkotlin/collections/AbstractSet;", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)Z", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AbstractMap$keys$1<K> extends AbstractSet<K> {
    final /* synthetic */ AbstractMap<K, V> this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractMap$keys$1(AbstractMap<K, ? extends V> r1) {
        this.this$0 = r1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object r1) {
        return this.this$0.containsKey(r1);
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.this$0.size();
    }

    @Override // kotlin.collections.AbstractSet, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<K> iterator() {
        return new AbstractMap$keys$1$iterator$1(this.this$0.entrySet().iterator());
    }
}
