package androidx.datastore.preferences.protobuf;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
class LazyField$LazyIterator<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> iterator;

    public LazyField$LazyIterator(Iterator<Map.Entry<K, Object>> r1) {
        this.iterator = r1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ Object next() {
        return next();
    }

    @Override // java.util.Iterator
    public Map.Entry<K, Object> next() {
        Map.Entry<K, Object> r0 = this.iterator.next();
        r0.getValue();
        return r0;
    }

    @Override // java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }
}
